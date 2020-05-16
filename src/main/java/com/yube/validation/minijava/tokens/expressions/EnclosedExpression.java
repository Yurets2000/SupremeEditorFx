package com.yube.validation.minijava.tokens.expressions;

public class EnclosedExpression extends Expression {

    private Expression innerExpression;

    public EnclosedExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
