package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FieldAccessExpression extends Expression {

    private Expression contextExpression;

    private String name;

    public FieldAccessExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
