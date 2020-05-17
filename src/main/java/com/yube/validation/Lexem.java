package com.yube.validation;

import lombok.Data;

@Data
public class Lexem {
    private final String name;
    private final String value;
    protected final int beginIndex, endIndex;
}
