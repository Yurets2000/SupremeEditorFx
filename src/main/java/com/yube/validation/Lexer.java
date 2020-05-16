package com.yube.validation;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class Lexer {

    protected Map<String, LexingRule> lexingRuleMap;

    public abstract List<Lexem> translate(String text);
}
