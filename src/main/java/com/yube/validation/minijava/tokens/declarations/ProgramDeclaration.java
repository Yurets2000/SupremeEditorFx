package com.yube.validation.minijava.tokens.declarations;

import com.yube.validation.Token;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProgramDeclaration extends Token {

    private List<ClassDeclaration> classDeclarations;

    public ProgramDeclaration(String value, int beginIndex, int endIndex) {
        super(value, beginIndex, endIndex);
    }
}
