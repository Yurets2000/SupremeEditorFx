package com.yube.validation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Parser {

    protected Map<String, Set<String>> rules;

    public Parser(String rulesPath) {
        ClassLoader classLoader = getClass().getClassLoader();
        String rules;
        try {
            Path path = Paths.get(classLoader.getResource(rulesPath).toURI());
            rules = Files.readAllLines(path).stream().collect(Collectors.joining());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Exception during rules reading", e);
        }

    }
}
