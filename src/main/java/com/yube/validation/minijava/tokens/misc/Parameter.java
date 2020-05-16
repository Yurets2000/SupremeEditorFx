package com.yube.validation.minijava.tokens.misc;

import com.yube.validation.Token;
import com.yube.validation.minijava.tokens.types.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Parameter extends Token {

    private Type type;

    private String name;

    public Parameter(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
