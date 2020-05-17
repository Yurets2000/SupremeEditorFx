package com.yube.validation.minijava.tokens.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrimitiveType extends Type {

    private PrimitiveTypeInstance instance;

    public PrimitiveType(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }

    public enum PrimitiveTypeInstance {
        INT, BOOLEAN
    }
}
