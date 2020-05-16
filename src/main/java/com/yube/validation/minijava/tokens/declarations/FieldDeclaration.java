package com.yube.validation.minijava.tokens.declarations;

import com.yube.validation.minijava.tokens.Token;
import com.yube.validation.minijava.tokens.types.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FieldDeclaration extends Token {

    private Type type;

    private String name;

    public FieldDeclaration(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
