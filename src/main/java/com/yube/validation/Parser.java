package com.yube.validation;

import lombok.Data;

import java.util.List;

@Data
public abstract class Parser {

    protected final RuleContext ruleContext;

    public abstract List<Token> translate(List<Lexem> lexems);
}
