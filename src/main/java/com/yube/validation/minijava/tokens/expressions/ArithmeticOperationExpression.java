package com.yube.validation.minijava.tokens.expressions;

public class ArithmeticOperationExpression extends ArithmeticBinaryExpression {

    private ArithmeticOperationType operationType;

    public ArithmeticOperationExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }

    public enum ArithmeticOperationType {
        BITWISE_AND, BITWISE_OR, BITWISE_XOR, LEFT_SHIFT, RIGHT_SHIFT, ZERO_FILL_RIGHT_SHIFT,
        ADDITION, SUBSTRACTION, MULTIPLICATION, DIVISION, MODULUS
    }
}
