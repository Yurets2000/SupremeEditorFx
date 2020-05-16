package com.yube.validation.minijava.tokens.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrimitiveType extends Type {

    private PrimitiveTypeInstance instance;

    public PrimitiveType(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }

    public enum PrimitiveTypeInstance {
        INT, BOOLEAN
    }
}
