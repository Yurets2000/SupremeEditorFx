package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MethodReferenceExpression extends Expression {

    private String name;

    private List<Expression> args;

    public MethodReferenceExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
