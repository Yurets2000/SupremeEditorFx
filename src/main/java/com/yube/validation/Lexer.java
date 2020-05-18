package com.yube.validation;

import lombok.Data;

import java.util.List;

@Data
public abstract class Lexer {

    protected final RuleContext ruleContext;

    public abstract List<Lexem> translate(String text);
}
