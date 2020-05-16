package com.yube.validation.minijava.tokens.statements;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnStatement extends Statement {

    private ExpressionStatement returnStatement;

    public ReturnStatement(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
