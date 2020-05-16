package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AssignExpression extends Expression {

    private AssignType assignType;

    public AssignExpression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }

    public enum AssignType {
        ASSIGN, ADD_ASSIGN, SUBSTRACT_ASSIGN, MULTIPLY_ASSIGN, DIVIDE_ASSIGN,
        MODULUS_ASSIGN, LEFT_SHIFT_ASSIGN, RIGHT_SHIFT_ASSIGN, BITWISE_AND_ASSIGN,
        BITWISE_EXLUSIVE_OR_ASSIGN, BIWISE_INCLUSIVE_OR_ASSING
    }
}
