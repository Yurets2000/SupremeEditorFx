package com.yube.validation.minijava;

import com.yube.validation.Lexem;
import com.yube.validation.Lexer;
import com.yube.validation.LexingRule;

import java.util.List;
import java.util.Map;

public class MiniJavaLexer extends Lexer {

    public MiniJavaLexer(Map<String, LexingRule> lexingRuleMap) {
        super(lexingRuleMap);
    }

    @Override
    public List<Lexem> translate(String text) {
        return null;
    }

}
