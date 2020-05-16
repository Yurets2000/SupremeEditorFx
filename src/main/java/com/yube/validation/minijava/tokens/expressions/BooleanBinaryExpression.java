package com.yube.validation.minijava.tokens.expressions;

public abstract class BooleanBinaryExpression extends BinaryExpression {

    public BooleanBinaryExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
