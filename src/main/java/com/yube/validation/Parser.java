package com.yube.validation;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class Parser {

    protected final Map<String, ParsingRule> parsingRuleMap;

    public abstract List<Token> translate(List<Lexem> lexems);
}
