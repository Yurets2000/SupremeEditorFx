package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArrayAccessExpression extends Expression {

    private Expression arrayExpression;

    private Expression numberExpression;

    public ArrayAccessExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
