package com.yube.validation.minijava.tokens.statements;

import com.yube.validation.minijava.tokens.expressions.Expression;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IfStatemet extends Statement {

    private Expression testExpression;

    private BlockStatement mainStatement;

    private BlockStatement elseStatement;

    public IfStatemet(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
