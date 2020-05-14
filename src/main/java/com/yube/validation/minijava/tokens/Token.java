package com.yube.validation.minijava.tokens;

import lombok.Data;

@Data
public abstract class Token {
    protected final String value;
    protected final int beginColumn, endColumn, beginRow, endRow;
}
