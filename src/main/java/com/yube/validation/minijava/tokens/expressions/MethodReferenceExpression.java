package com.yube.validation.minijava.tokens.expressions;

import java.util.List;

public class MethodReferenceExpression extends Expression {

    private String name;

    private List<Expression> args;

    public MethodReferenceExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
