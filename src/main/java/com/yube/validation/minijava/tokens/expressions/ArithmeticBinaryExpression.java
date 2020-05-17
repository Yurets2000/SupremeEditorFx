package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ArithmeticBinaryExpression extends BinaryExpression {

    public ArithmeticBinaryExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
