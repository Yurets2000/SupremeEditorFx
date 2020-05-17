package com.yube.validation.minijava;

import com.yube.validation.Lexem;
import com.yube.validation.Lexer;
import com.yube.validation.LexingRule;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MiniJavaLexer extends Lexer {

    private Map<String, LexingRule> reversedLexingRules;

    public MiniJavaLexer(Map<String, LexingRule> lexingRuleMap) {
        super(lexingRuleMap);
        reversedLexingRules = lexingRuleMap.entrySet().stream()
                .map(e -> new HashMap.SimpleEntry<>(e.getValue().getValue(), e.getValue()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    @Override
    public List<Lexem> translate(String text) {
        return null;
    }

}
