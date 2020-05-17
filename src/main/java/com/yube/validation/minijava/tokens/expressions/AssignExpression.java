package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AssignExpression extends Expression {

    private AssignType assignType;

    public AssignExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }

    public enum AssignType {
        ASSIGN, ADD_ASSIGN, SUBSTRACT_ASSIGN, MULTIPLY_ASSIGN, DIVIDE_ASSIGN,
        MODULUS_ASSIGN, BITWISE_AND_ASSIGN,
        BITWISE_EXLUSIVE_OR_ASSIGN, BIWISE_INCLUSIVE_OR_ASSING
    }
}
