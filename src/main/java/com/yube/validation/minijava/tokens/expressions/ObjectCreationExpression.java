package com.yube.validation.minijava.tokens.expressions;

public class ObjectCreationExpression extends Expression {

    private String name;

    public ObjectCreationExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
