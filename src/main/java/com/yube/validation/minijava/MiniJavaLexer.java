package com.yube.validation.minijava;

import com.yube.validation.Lexem;
import com.yube.validation.Lexer;
import com.yube.validation.LexingRule;
import com.yube.validation.exceptions.LexemResolutionException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniJavaLexer extends Lexer {

    private LinkedHashMap<Pattern, LexingRule> reversedLexingRules;

    public MiniJavaLexer(LinkedHashMap<String, LexingRule> lexingRuleMap) {
        super(lexingRuleMap);
        reversedLexingRules = new LinkedHashMap<>();
        lexingRuleMap.entrySet().stream()
                .map(e -> new LinkedHashMap.SimpleEntry<>(e.getValue().getPattern(), e.getValue()))
                .forEach(e -> reversedLexingRules.put(e.getValue().getPattern(), e.getValue()));
    }

    @Override
    public List<Lexem> translate(String text) {
        StringBuilder temp = new StringBuilder(text);
        Set<Pattern> keys = reversedLexingRules.keySet();
        List<Lexem> lexems = new ArrayList<>();
        int i = 0;
        while(temp.length() != 0) {
            int start = 0, end = 0;
            List<Lexem> matches = new ArrayList<>();
            i += deleteTrailingWhitespaces(temp);
            for(Pattern pattern : keys) {
                if(matches.isEmpty()) {
                    Pattern pattern2 = Pattern.compile("^" + pattern.pattern());
                    Matcher matcher = pattern2.matcher(temp.toString());
                    if (matcher.find()) {
                        start = matcher.start();
                        end = matcher.end();
                        String value = matcher.group().trim();
                        LexingRule rule = reversedLexingRules.get(pattern);
                        Lexem lexem = new Lexem(rule.getName(), value, i + start, i + end);
                        matches.add(lexem);
                    }
                }
            }
            if(matches.isEmpty()) throw new LexemResolutionException("Unknown lexems was found lexical processing");
            Lexem lexem = matches.get(0);
            lexems.add(lexem);
            temp.delete(start, end);
            i += (end - start);
        }
        return lexems;
    }

    private int deleteTrailingWhitespaces(StringBuilder temp) {
        int j = trailingWhitespaceCount(temp.toString());
        if(j > 0){
            if(j == 1) {
                temp.deleteCharAt(0);
            } else {
                temp.delete(0, j);
            }
        }
        return j;
    }

    private int trailingWhitespaceCount(String text) {
        Pattern pattern = Pattern.compile("^\\s+");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()) {
            return matcher.end();
        }
        return 0;
    }
}
