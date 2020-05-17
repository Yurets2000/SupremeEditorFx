package com.yube.validation.minijava.tokens.expressions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NullLiteralExpression extends LiteralExpression {

    public NullLiteralExpression(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
