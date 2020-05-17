package com.yube.validation;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

@Data
public abstract class Lexer {

    protected final LinkedHashMap<String, LexingRule> lexingRuleMap;

    public abstract List<Lexem> translate(String text);
}
