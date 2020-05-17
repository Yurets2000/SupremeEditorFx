package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BooleanCompareExpression extends BooleanBinaryExpression {

    public BooleanCompareExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
