package com.yube.validation.minijava.tokens.statements;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class BlockStatement extends Statement {

    private List<Statement> statements;

    public BlockStatement(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
