package com.yube.validation.minijava.tokens.expressions;

public class FieldAccessExpression extends Expression {

    private Expression contextExpression;

    private String name;

    public FieldAccessExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
