package com.yube.validation.minijava.tokens.statements;

import com.yube.validation.Token;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Statement extends Token {

    public Statement(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
