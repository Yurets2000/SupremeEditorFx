package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BooleanLiteralExpression extends LiteralExpression {

    public BooleanLiteralExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
