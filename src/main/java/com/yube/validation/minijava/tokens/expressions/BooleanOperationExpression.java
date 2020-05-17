package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BooleanOperationExpression extends BooleanBinaryExpression {

    public BooleanOperationExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
