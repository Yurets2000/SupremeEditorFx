package com.yube.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(callSuper = false)
public class LexingRule extends Rule {

    private Pattern pattern;

    public LexingRule(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "LexingRule{" +
                "pattern=" + pattern.pattern() +
                "} " + super.toString();
    }
}
