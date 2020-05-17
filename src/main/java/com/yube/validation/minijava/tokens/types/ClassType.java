package com.yube.validation.minijava.tokens.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClassType extends Type {

    private String name;

    public ClassType(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
