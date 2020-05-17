package com.yube.validation;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

@Data
public abstract class Parser {

    protected final LinkedHashMap<String, ParsingRule> parsingRuleMap;

    public abstract List<Token> translate(List<Lexem> lexems);
}
