package com.yube.validation.minijava.tokens.expressions;

public abstract class LiteralExpression extends Expression {

    public LiteralExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
