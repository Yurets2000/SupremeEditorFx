package com.yube.validation.minijava.tokens.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArrayType extends Type {

    private Type baseType;

    public ArrayType(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
