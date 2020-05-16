package com.yube.validation.minijava.tokens.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VoidType extends Type {

    public VoidType(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
