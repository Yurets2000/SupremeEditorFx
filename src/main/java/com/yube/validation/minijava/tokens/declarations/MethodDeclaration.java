package com.yube.validation.minijava.tokens.declarations;

import com.yube.validation.minijava.tokens.Token;
import com.yube.validation.minijava.tokens.expressions.Expression;
import com.yube.validation.minijava.tokens.misc.Parameter;
import com.yube.validation.minijava.tokens.statements.Statement;
import com.yube.validation.minijava.tokens.types.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MethodDeclaration extends Token {

    private Type type;

    private String name;

    private List<Parameter> parameters;

    private List<VariableDeclaration> variableDeclarations;

    private List<Statement> statements;

    private Expression returnExpression;

    public MethodDeclaration(String value, int beginColumn, int endColumn, int beginRow, int endRow) {
        super(value, beginColumn, endColumn, beginRow, endRow);
    }
}
