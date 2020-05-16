package com.yube.validation.minijava.tokens.expressions;

import java.util.List;

public class MethodCallExpression extends Expression {

    private Expression contextExpression;

    private String name;

    private List<Expression> args;

    public MethodCallExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
