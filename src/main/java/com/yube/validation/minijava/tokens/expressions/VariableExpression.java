package com.yube.validation.minijava.tokens.expressions;

public class VariableExpression extends Expression {

    private String name;

    public VariableExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
