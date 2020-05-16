package com.yube.validation.minijava.tokens.declarations;

import com.yube.validation.minijava.tokens.Token;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClassDeclaration extends Token {

    private String name;

    private List<FieldDeclaration> fieldDeclarations;

    private List<MethodDeclaration> methodDeclarations;

    public ClassDeclaration(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
