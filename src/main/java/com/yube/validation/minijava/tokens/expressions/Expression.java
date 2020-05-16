package com.yube.validation.minijava.tokens.expressions;

import com.yube.validation.Token;
import com.yube.validation.minijava.tokens.types.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Expression extends Token {

    protected Type resultType;

    public Expression(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
