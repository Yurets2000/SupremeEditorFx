package com.yube.validation.minijava.tokens.declarations;

import com.yube.validation.Token;
import com.yube.validation.minijava.tokens.types.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VariableDeclaration extends Token {

    private Type type;

    private String name;

    public VariableDeclaration(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}