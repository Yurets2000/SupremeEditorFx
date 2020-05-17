package com.yube.validation;

import com.yube.validation.exceptions.RuleResolutionException;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RuleContext {

    private final static Pattern LEXING_RULE_PATTERN = Pattern.compile("[A-Z_]+\\s+:\\s+'.+';");
    private final static Pattern PARSING_RULE_PATTERN = Pattern.compile("[A-Z_]+\\s+:[^;]+;");
    private final static Pattern GROUP_PATTERN = Pattern.compile("^\\([^;]+\\)[?*+]?$");
    private final static Pattern SIMPLE_GROUP_PATTERN = Pattern.compile("^\\([^;]+\\)$");
    private final static Pattern ZERO_OR_ONE_PATTERN = Pattern.compile("^\\([^;]+\\)\\?$");
    private final static Pattern ZERO_OR_MORE_PATTERN = Pattern.compile("^\\([^;]+\\)\\*$");
    private final static Pattern ONE_OR_MORE_PATTERN = Pattern.compile("^\\([^;]+\\)\\+$");
    @Getter
    private Map<String, LexingRule> lexingRuleMap;
    @Getter
    private Map<String, ParsingRule> parsingRuleMap;

    public RuleContext(String lexingRulesPath, String parsingRulesPath) {
        String lexingRules = extractRules(lexingRulesPath);
        String parsingRules = extractRules(parsingRulesPath);
        initLexingRules(lexingRules);
        initParsingRules(parsingRules);
    }

    private String extractRules(String rulesPath) {
        ClassLoader classLoader = getClass().getClassLoader();
        String rules;
        try {
            URI uri = classLoader.getResource(rulesPath).toURI();
            Path path = Paths.get(uri);
            rules = Files.readAllLines(path).stream().collect(Collectors.joining("\n\r"));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Exception during rules extracting", e);
        }
        return rules.trim();
    }

    private void initLexingRules(String lexingRules) {
        List<String> rules = getGroups(LEXING_RULE_PATTERN, lexingRules);
        if (rules.isEmpty()) throw new RuleResolutionException("No lexing rules was resolved");
        lexingRuleMap = new HashMap<>();
        rules.stream()
                .map(t -> t.split(":"))
                .peek(arr -> {
                    arr[0] = arr[0].trim();
                    System.out.println(arr[1].trim());
                    arr[1] = arr[1].trim().replace("'", "");
                    arr[1] = arr[1].substring(0, arr[1].length() - 1);
                }).forEach(arr -> {
            LexingRule rule = new LexingRule(arr[1]);
            if (contains(arr[1], Arrays.asList("*", "+", "?", ".", "(", ")", "{", "}", "[", "]", "^", ":", "|"))) {
                rule.setPattern(Pattern.compile(Pattern.quote(arr[1])));
            } else {
                rule.setPattern(Pattern.compile(arr[1]));
            }
            lexingRuleMap.put(arr[0], rule);
        });
    }

    private void initParsingRules(String parsingRules) {
        List<String> rules = getGroups(PARSING_RULE_PATTERN, parsingRules);
        if (rules.isEmpty()) throw new RuleResolutionException("No parsing rules was resolved");
        parsingRuleMap = new HashMap<>();
        rules.stream()
                .map(t -> t.split(":"))
                .peek(arr -> {
                    arr[0] = arr[0].trim();
                    arr[1] = arr[1].trim();
                    arr[1] = arr[1].substring(0, arr[1].length() - 1);
                }).forEach(arr -> parsingRuleMap.put(arr[0], formParsingRule(arr[1])));
    }

    private ParsingRule formParsingRule(String value) {
        ParsingRule rule = new ParsingRule(value);
        String innerValue;
        StringBuilder builder = new StringBuilder(value);
        if (value.matches(GROUP_PATTERN.pattern()) && checkBracket(value)) {
            builder.deleteCharAt(0);
            if (value.matches(SIMPLE_GROUP_PATTERN.pattern())) {
                builder.deleteCharAt(builder.length() - 1);
                rule.setCount(ParsingRule.ComponentsCount.ONE);
            } else {
                builder.deleteCharAt(builder.length() - 1);
                builder.deleteCharAt(builder.length() - 1);
                if (value.matches(ZERO_OR_ONE_PATTERN.pattern())) {
                    rule.setCount(ParsingRule.ComponentsCount.ZERO_OR_ONE);
                } else if (value.matches(ZERO_OR_MORE_PATTERN.pattern())) {
                    rule.setCount(ParsingRule.ComponentsCount.ZERO_OR_MORE);
                } else if (value.matches(ONE_OR_MORE_PATTERN.pattern())) {
                    rule.setCount(ParsingRule.ComponentsCount.ONE_OR_MORE);
                } else {
                    throw new RuleResolutionException("Unknown group type found");
                }
            }
        } else {
            rule.setCount(ParsingRule.ComponentsCount.ONE);
        }
        innerValue = builder.toString().trim();
        List<String> parts = breakOnPartsUsingDelimiter(innerValue, '|');
        if (parts.size() == 1) {
            String part = parts.get(0);
            List<String> subParts = breakOnPartsUsingDelimiter(part, ' ');
            if (subParts.size() == 1) {
                if(rule.getCount() != ParsingRule.ComponentsCount.ONE) {
                    List<ParsingRule> ruleComponents = subParts.stream().map(this::formParsingRule).collect(Collectors.toList());
                    rule.setRuleComponents(ruleComponents);
                } else {
                    rule.setTerminal(true);
                }
                return rule;
            } else {
                List<ParsingRule> ruleComponents = subParts.stream().map(this::formParsingRule).collect(Collectors.toList());
                rule.setRuleComponents(ruleComponents);
            }
        } else {
            List<ParsingRule> ruleComponents = parts.stream().map(this::formParsingRule).collect(Collectors.toList());
            rule.setRuleComponents(ruleComponents);
        }
        return rule;
    }

    private List<String> getGroups(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        List<String> groups = new ArrayList<>();
        while (matcher.find()) {
            groups.add(matcher.group().trim());
        }
        return groups;
    }

    private List<String> breakOnPartsUsingDelimiter(String text, char delimiter) {
        List<String> parts = new ArrayList<>();
        boolean flag = true;
        int i = 0, j = 0;
        while (flag) {
            if (i >= text.length()) {
                parts.add(text.substring(j).trim());
                flag = false;
            } else if (text.charAt(i) == delimiter) {
                parts.add(text.substring(j, i).trim());
                j = i + 1;
            } else if (text.charAt(i) == '(') {
                int k = findCloseBracketPosition(text, i);
                if (k < text.length() && Arrays.asList('*', '?', '+').contains(text.charAt(k))) {
                    k = k + 1;
                } else if (k < text.length() && text.charAt(k) != ' ') {
                    throw new RuleResolutionException("Unknown group type found");
                }
                i = k - 1;
            }
            i++;
        }
        return parts;
    }

    private int findCloseBracketPosition(String text, int startBracketPosition) {
        int openBracketsCount = 1;
        int i = startBracketPosition + 1;
        while (openBracketsCount != 0) {
            if (i == text.length()) throw new RuleResolutionException("Open/Close brackets count mismatch");
            if (text.charAt(i) == '(') {
                openBracketsCount++;
            } else if (text.charAt(i) == ')') {
                openBracketsCount--;
            }
            i++;
        }
        return i;
    }

    private boolean checkBracket(String text) {
        if(text.length() == 2) return text.contains("(") && text.contains(")");
        if(!checkBracketsCount(text)) return false;
        if(!(text.contains("(") || text.contains(")"))) return true;
        int begin = text.indexOf("(") + 1;
        int end = text.lastIndexOf(")");
        return checkBracket(text.substring(begin, end));
    }

    private boolean checkBracketsCount(String text) {
        if(text.length() < 2) return false;
        if(!text.contains("(")) {
            return !text.contains(")");
        } else {
            int i = text.indexOf("(") + 1;
            int openBracketsCount = 1;
            while (openBracketsCount != 0) {
                if (i == text.length()) return false;
                if (text.charAt(i) == '(') {
                    openBracketsCount++;
                } else if (text.charAt(i) == ')') {
                    openBracketsCount--;
                }
                i++;
            }
        }
        return true;
    }

    private boolean contains(String text, List<String> subs) {
        for (String sub : subs) {
            if (text.contains(sub)) {
                return true;
            }
        }
        return false;
    }
}
