package com.yube.validation.minijava.tokens.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VoidType extends Type {

    public VoidType(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
