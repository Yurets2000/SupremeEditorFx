package com.yube.validation.minijava.tokens.expressions;

public abstract class BinaryExpression extends Expression {

    protected Expression leftExpression;

    protected Expression rightExpression;

    public BinaryExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
