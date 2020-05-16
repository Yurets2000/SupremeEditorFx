package com.yube.validation.minijava;

import com.yube.validation.Lexem;
import com.yube.validation.LexingRule;
import com.yube.validation.Parser;
import com.yube.validation.Token;

import java.util.List;
import java.util.Map;

public class MiniJavaParser extends Parser {

    public MiniJavaParser(Map<String, LexingRule> parsingRuleMap) {
        super(parsingRuleMap);
    }

    @Override
    public List<Token> translate(List<Lexem> lexems) {
        return null;
    }
}
