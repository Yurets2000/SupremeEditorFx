package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EnclosedExpression extends Expression {

    private Expression innerExpression;

    public EnclosedExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
