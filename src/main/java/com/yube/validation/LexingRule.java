package com.yube.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper = false)
public class LexingRule extends Rule {

    private final String name;
    private Pattern pattern;

    public LexingRule(String name, String value) {
        super(value);
        this.name = name;
    }

    @Override
    public String toString() {
        return "LexingRule{" +
                "pattern=" + pattern.pattern() +
                "} " + super.toString();
    }
}
