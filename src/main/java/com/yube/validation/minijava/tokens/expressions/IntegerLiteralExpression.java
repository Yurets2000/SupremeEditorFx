package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IntegerLiteralExpression extends LiteralExpression {

    public IntegerLiteralExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
