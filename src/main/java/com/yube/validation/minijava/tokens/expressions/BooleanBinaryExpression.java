package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BooleanBinaryExpression extends BinaryExpression {

    public BooleanBinaryExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
