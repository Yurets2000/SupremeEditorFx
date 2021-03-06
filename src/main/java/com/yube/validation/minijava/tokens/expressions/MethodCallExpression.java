package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MethodCallExpression extends Expression {

    private Expression contextExpression;

    private String name;

    private List<Expression> args;

    public MethodCallExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
