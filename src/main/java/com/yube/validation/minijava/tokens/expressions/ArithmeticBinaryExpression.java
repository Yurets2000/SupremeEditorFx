package com.yube.validation.minijava.tokens.expressions;

public abstract class ArithmeticBinaryExpression extends BinaryExpression {

    public ArithmeticBinaryExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
