package com.yube.validation.minijava.tokens.statements;

import com.yube.validation.minijava.tokens.expressions.Expression;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WhileStatement extends Statement {

    private Expression testExpression;

    private BlockStatement innerStatement;

    public WhileStatement(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
