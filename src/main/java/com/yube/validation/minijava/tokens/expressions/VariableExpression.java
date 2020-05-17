package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VariableExpression extends Expression {

    private String name;

    public VariableExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
