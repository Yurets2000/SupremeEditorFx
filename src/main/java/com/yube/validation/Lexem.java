package com.yube.validation;

import lombok.Data;

@Data
public class Lexem extends Token {

    private final String name;

    public Lexem(String name, String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
        this.name = name;
    }
}
