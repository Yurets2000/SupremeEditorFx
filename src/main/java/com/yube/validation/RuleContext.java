package com.yube.validation;

import lombok.Getter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RuleContext {

    @Getter
    private Map<String, Pattern> lexerRules;
    @Getter
    private Map<String, Set<String>> parserRules;

    public RuleContext(String lexingRulesPath, String parsingRulesPath) {
        String lexingRules = extractRules(lexingRulesPath);
        String parsingRules = extractRules(parsingRulesPath);
        initLexerRules(lexingRules);
        initParserRules(parsingRules);
    }

    private String extractRules(String rulesPath) {
        ClassLoader classLoader = getClass().getClassLoader();
        String rules;
        try {
            Path path = Paths.get(classLoader.getResource(rulesPath).toURI());
            rules = Files.readAllLines(path).stream().collect(Collectors.joining());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Exception during rules extracting", e);
        }
        return normalize(rules);
    }

    private String normalize(String raw) {
        return raw.trim().replace("\n", "").replace("\r", "");
    }

    private void initLexerRules(String lexingRules) {
        Pattern rulePattern = Pattern.compile("[A-Z_]+ : .+;");
        Set<String> rules = getGroups(rulePattern, lexingRules);
        lexerRules = new HashMap<>();
        rules.stream()
                .peek(r -> r.replace(";", ""))
                .map(t -> t.split(":"))
                .peek(arr -> {
                    arr[0] = arr[0].trim();
                    arr[1] = arr[1].trim().replace("\'", "");
                }).forEach(arr -> lexerRules.put(arr[0], Pattern.compile(arr[1])));
    }

    private void initParserRules(String parsingRules) {
        Pattern rulePattern = Pattern.compile("[A-Z_]+ : .+;");
        Set<String> rules = getGroups(rulePattern, parsingRules);
        parserRules = new HashMap<>();
        Pattern rulePartPattern = Pattern.compile("(([A-Z_]+|\\([A-Z_]+\\)[*+?])\\s?)+");
        rules.stream()
                .peek(r -> r.replace(";", ""))
                .map(t -> t.split(":"))
                .peek(arr -> {
                    arr[0] = arr[0].trim();
                    arr[1] = arr[1].trim();
                }).forEach(arr -> parserRules.put(arr[0], getGroups(rulePartPattern, arr[1])));
    }

    private Set<String> getGroups(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        Set<String> groups = new HashSet<>();
        while (matcher.matches()) {
            groups.add(matcher.group().trim());
        }
        return groups;
    }
}
