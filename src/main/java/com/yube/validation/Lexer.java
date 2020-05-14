package com.yube.validation;

import java.util.Map;
import java.util.regex.Pattern;

public abstract class Lexer {

    protected Map<String, Pattern> rules;

    public abstract String translate(String text);
}
