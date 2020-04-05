package com.yube.encoding.hierarchy;

import com.yube.configuration.processors.encodings.EncodingsProcessor;
import com.yube.encoding.exceptions.EncodingHandlingException;
import com.yube.encoding.utils.EncodingHelper;

import java.util.*;
import java.util.stream.Collectors;

public final class UTF8Handler extends AlgorithmicEncodingHandler {

    public UTF8Handler(String encoding, EncodingsProcessor encodingsProcessor) {
        super(encoding, encodingsProcessor);
    }

    @Override
    public byte[] encode(String text) {
        List<String> symbols = EncodingHelper.splitTextOnSymbols(text);
        Set<String> uniqueSymbols = new HashSet<>(symbols);
        Map<String, byte[]> symbolsMap = new HashMap<>();
        try {
            for (String symbol : uniqueSymbols) {
                int codepoint = EncodingHelper.getCodepoint(symbol);
                byte[] bytesFromCodepoint = bytesFromCodepoint(codepoint);
                symbolsMap.put(symbol, bytesFromCodepoint);
            }
        } catch (Exception e){
            throw new EncodingHandlingException("Some symbols can't be encoded with such encoding", e);
        }

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
        StringBuilder result = new StringBuilder();

        ArrayDeque<String> bitBlocks = new ArrayDeque<>();
        for (byte b : bytes) {
            bitBlocks.offer(EncodingHelper.byteToBitBlock(b));
        }

        try {
            while (!bitBlocks.isEmpty()) {
                String firstBlock = bitBlocks.peek();
                int bytesForCodepointCount = countBytesForCodepointDecoding(firstBlock);
                String[] bytesForCodepoint = new String[bytesForCodepointCount];
                for (int i = 0; i < bytesForCodepointCount; i++) {
                    bytesForCodepoint[i] = bitBlocks.poll();
                }
                int codePoint = codepointFromBytes(bytesForCodepoint);
                result.append(EncodingHelper.codepointToString(codePoint));
            }
        } catch (Exception e){
            throw new EncodingHandlingException("Some symbols can't be decoded with such encoding", e);
        }
        return result.toString();
    }

    private byte[] bytesFromCodepoint(int codepoint) {
        int bytesForCodepointCount = countBytesForCodepointEncoding(codepoint);
        byte[] bRes = new byte[bytesForCodepointCount];
        if (bytesForCodepointCount == 1) {
            String bitBlock = EncodingHelper.intToBitBlock(codepoint, 7);
            bRes[0] = (byte) Integer.parseInt(bitBlock, 2);
        } else if (bytesForCodepointCount > 1 && bytesForCodepointCount <= 4) {
            int bitBlockLength = (7 - bytesForCodepointCount) + (bytesForCodepointCount - 1) * 6;
            String bitBlock = EncodingHelper.intToBitBlock(codepoint, bitBlockLength);
            String[] sRes = new String[bytesForCodepointCount];
            int counter = bitBlockLength - 1;
            for (int i = bytesForCodepointCount - 1; i > 0; i--) {
                sRes[i] = "";
                for (int j = 0; j < 6; j++) {
                    sRes[i] = bitBlock.charAt(counter--) + sRes[i];
                }
                sRes[i] = "10" + sRes[i];
            }
            sRes[0] = "";
            for (int j = 0; j < 7 - bytesForCodepointCount; j++) {
                sRes[0] = bitBlock.charAt(counter--) + sRes[0];
            }
            sRes[0] = String.format("%" + bytesForCodepointCount + "s0", "").replace(' ', '1') + sRes[0];
            for (int i = 0; i < bytesForCodepointCount; i++) {
                bRes[i] = (byte) Integer.parseInt(sRes[i], 2);
            }
        }
        return bRes;
    }

    private int codepointFromBytes(String[] bytes) {
        StringBuilder codepoint = new StringBuilder();
        if (bytes.length == 1) {
            for (int i = 1; i < 8; i++) {
                codepoint.append(bytes[0].charAt(i));
            }
        } else if (bytes.length > 1 && bytes.length <= 4) {
            for (int i = bytes.length + 1; i < 8; i++) {
                codepoint.append(bytes[0].charAt(i));
            }
            for (int j = 1; j <= bytes.length - 1; j++) {
                for (int i = 2; i < 8; i++) {
                    codepoint.append(bytes[j].charAt(i));
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(codepoint.toString(), 2);
    }

    private int countBytesForCodepointEncoding(int codepoint) {
        if (codepoint < 0) throw new IllegalArgumentException();
        if (codepoint < 0x80) return 1;
        else if (codepoint < 0x800) return 2;
        else if (codepoint < 0x10000) return 3;
        else if (codepoint < 0x20000) return 4;
        throw new IllegalArgumentException();
    }

    private int countBytesForCodepointDecoding(String block) {
        if (block.charAt(0) == '0') return 1;
        else if (block.substring(0, 3).equals("110")) return 2;
        else if (block.substring(0, 4).equals("1110")) return 3;
        else if (block.substring(0, 5).equals("11110")) return 4;
        throw new IllegalArgumentException();
    }

}
