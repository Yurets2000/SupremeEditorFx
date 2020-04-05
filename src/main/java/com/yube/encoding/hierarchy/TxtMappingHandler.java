package com.yube.encoding.hierarchy;

import com.yube.configuration.processors.encodings.EncodingsProcessor;
import com.yube.configuration.processors.encodings.MappingProcessor;
import com.yube.encoding.exceptions.EncodingHandlingException;
import com.yube.encoding.utils.EncodingHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class TxtMappingHandler extends MappingEncodingHandler {

    private final Pattern UNICODE_PATTERN = Pattern.compile("0x[0-9A-F]{4,6}");
    private final Pattern BYTE_PATTERN = Pattern.compile("0x[0-9A-F]{2}");

    public TxtMappingHandler(String encoding, EncodingsProcessor encodingsProcessor, MappingProcessor mappingProcessor) {
        super(encoding, encodingsProcessor, mappingProcessor);
        ClassLoader classLoader = getClass().getClassLoader();
        this.mappingFile = new File(classLoader.getResource(mapping.getPath()).getFile());
    }

    @Override
    public byte[] encode(String text) {
        List<String> symbols = EncodingHelper.splitTextOnSymbols(text);
        Set<String> uniqueSymbols = new HashSet<>(symbols);

        Map<String, String> symbolCodepointMap = uniqueSymbols.stream()
                .collect(Collectors.toMap(
                        s -> s,
                        s -> EncodingHelper.getUnicodeRepresentation(EncodingHelper.getCodepoint(s))));
        Collection<String> codepointList = symbolCodepointMap.values();
        Map<String, byte[]> codepointByteMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(mappingFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                for(String codepoint : codepointList){
                    if(line.contains(codepoint)){
                        Matcher bm = BYTE_PATTERN.matcher(line);
                        int length = encoding.getLength();
                        byte[] arr = new byte[length];
                        for (int i = 0; i < length; i++) {
                            if(bm.find()) {
                                String b = bm.group();
                                arr[i] = (byte) Integer.parseInt(b.substring(2), 16);
                            }
                        }
                        codepointByteMap.put(codepoint, arr);
                    }
                }
            }
            if(codepointByteMap.keySet().size() < codepointList.size())
                throw new EncodingHandlingException("Some symbols can't be encoded with such encoding");
        } catch (IOException e) {
            throw new EncodingHandlingException("ImplementorException due processing mapping file", e);
        }

        Map<String, byte[]> symbolsMap = symbolCodepointMap.entrySet().stream().collect(Collectors.toMap(
            entry -> entry.getKey(),
            entry -> codepointByteMap.get(entry.getValue())
        ));
        List<Byte> resList = new ArrayList<>();
        List<byte[]> symbolBytes = symbols.stream().map(symbolsMap::get).collect(Collectors.toList());
        for(byte[] arr : symbolBytes){
            for (byte b : arr) {
                resList.add(b);
            }
        }
        byte[] result = new byte[resList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resList.get(i);
        }
        return result;
    }

    @Override
    public String decode(byte[] bytes) {
        if(bytes.length == 0) return "";

        int counter = 0;
        List<String> symbolBytesList = new ArrayList<>();
        int length = encoding.getLength();
        for (int i = 0; i < bytes.length / length; i++) {
            StringBuilder arr = new StringBuilder();
            for (int j = 0; j < length; j++) {
                if(j < length - 1) {
                    arr.append(EncodingHelper.byteToByteBlock(bytes[counter++]) + " ");
                }else{
                    arr.append(EncodingHelper.byteToByteBlock(bytes[counter++]));
                }
            }
            symbolBytesList.add(arr.toString());
        }
        Set<String> symbolBytesSet = new HashSet<>(symbolBytesList);
        Map<String, String> symbolBytesMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(mappingFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                for(String symbolBytes : symbolBytesSet){
                    if(line.contains(symbolBytes)){
                        Matcher bm = UNICODE_PATTERN.matcher(line);
                        if(bm.find()) {
                            String unicode = bm.group();
                            int codepoint = EncodingHelper.getCodepointFromUnicodeRepresentation(unicode);
                            symbolBytesMap.put(symbolBytes, EncodingHelper.codepointToString(codepoint));
                        }
                    }
                }
            }
            if(symbolBytesMap.keySet().size() < symbolBytesSet.size())
                throw new EncodingHandlingException("Some symbols can't be decoded with such encoding");
        } catch (IOException e) {
            throw new EncodingHandlingException("ImplementorException due processing from mapping file", e);
        }

        return symbolBytesList.stream().map(symbolBytesMap::get).collect(Collectors.joining());
    }


}
