package com.yube.services;

import com.yube.encoding.factory.EncodingHandlerFactory;
import com.yube.encoding.hierarchy.EncodingHandler;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public final class FileService {

    private EncodingHandlerFactory encodingHandlerFactory;

    public FileService(EncodingHandlerFactory encodingHandlerFactory){
        this.encodingHandlerFactory = encodingHandlerFactory;
    }

    public String readFromFile(File file, String encoding) throws IOException {
        byte[] content = Files.readAllBytes(Paths.get(file.toURI()));
        EncodingHandler encodingHandler = encodingHandlerFactory.getEncodingHandler(encoding);
        return encodingHandler.decode(content);
    }

    public void writeInFile(File file, String text, String encoding) throws IOException {
        EncodingHandler encodingHandler = encodingHandlerFactory.getEncodingHandler(encoding);
        Files.write(Paths.get(file.toURI()), encodingHandler.encode(text), StandardOpenOption.WRITE);
    }
}
