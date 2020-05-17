package com.yube.validation.minijava;

import com.yube.validation.Lexem;
import com.yube.validation.Parser;
import com.yube.validation.ParsingRule;
import com.yube.validation.Token;

import java.util.LinkedHashMap;
import java.util.List;

public class MiniJavaParser extends Parser {

    public MiniJavaParser(LinkedHashMap<String, ParsingRule> parsingRuleMap) {
        super(parsingRuleMap);
    }

    @Override
    public List<Token> translate(List<Lexem> lexems) {
        return null;
    }
}
