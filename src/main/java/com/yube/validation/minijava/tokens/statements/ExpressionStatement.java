package com.yube.validation.minijava.tokens.statements;

import com.yube.validation.minijava.tokens.expressions.Expression;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExpressionStatement extends Statement {

    private Expression expression;

    public ExpressionStatement(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
