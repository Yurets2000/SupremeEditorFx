package com.yube.validation.minijava.tokens.expressions;

import com.yube.validation.minijava.tokens.types.Type;

public class ArrayCreationExpression extends Expression {

    private Type arrayType;

    private Expression countExpression;

    public ArrayCreationExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
