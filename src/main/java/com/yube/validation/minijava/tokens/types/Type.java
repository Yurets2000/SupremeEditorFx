package com.yube.validation.minijava.tokens.types;

import com.yube.validation.Token;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Type extends Token {

    public Type(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
