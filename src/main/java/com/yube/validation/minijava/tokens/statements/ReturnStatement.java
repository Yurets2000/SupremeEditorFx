package com.yube.validation.minijava.tokens.statements;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnStatement extends Statement {

    private ExpressionStatement returnStatement;

    public ReturnStatement(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
