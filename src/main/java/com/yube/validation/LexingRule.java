package com.yube.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.regex.Pattern;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LexingRule extends Rule {

    private final String name;
    private Pattern pattern;

    public LexingRule(String name, String value) {
        super(value);
        this.name = name;
    }
}
