package com.yube.validation.minijava.tokens.expressions;

public abstract class UnaryExpression extends Expression {

    public UnaryExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
