package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArithmeticCompareExpression extends ArithmeticBinaryExpression {

    private ArithmeticCompareType compareType;

    public ArithmeticCompareExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }

    public enum ArithmeticCompareType {
        EQUAL, NOT_EQUAL, GREATER, LESS, GREATER_OR_EQUAL, LESS_OR_EQUAL
    }
}
