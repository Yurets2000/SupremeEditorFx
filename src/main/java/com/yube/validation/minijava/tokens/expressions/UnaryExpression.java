package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class UnaryExpression extends Expression {

    public UnaryExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
