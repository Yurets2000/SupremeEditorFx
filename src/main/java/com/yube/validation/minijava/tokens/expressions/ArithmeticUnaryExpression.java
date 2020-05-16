package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArithmeticUnaryExpression extends UnaryExpression {

    private ArithmeticUnaryType unaryType;

    public ArithmeticUnaryExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }

    public enum ArithmeticUnaryType {
        PRE_INCREMENT, POST_INCREMENT, PRE_DECREMENT, POST_DECREMENT, BITWISE_COMPLIMENT
    }
}
