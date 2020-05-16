package com.yube.validation.minijava.tokens.expressions;

public class ArrayAccessExpression extends Expression {

    private Expression arrayExpression;

    private Expression numberExpression;

    public ArrayAccessExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
