package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArithmeticOperationExpression extends ArithmeticBinaryExpression {

    private ArithmeticOperationType operationType;

    public ArithmeticOperationExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }

    public enum ArithmeticOperationType {
        BITWISE_AND, BITWISE_OR, BITWISE_XOR, LEFT_SHIFT, RIGHT_SHIFT, ZERO_FILL_RIGHT_SHIFT,
        ADDITION, SUBSTRACTION, MULTIPLICATION, DIVISION, MODULUS
    }
}
