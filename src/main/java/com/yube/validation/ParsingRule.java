package com.yube.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ParsingRule extends Rule {

    private List<? extends Rule> ruleComponents;
    private ComponentsCount count;
    private boolean terminal;

    public ParsingRule(String value) {
        super(value);
    }

    public enum ComponentsCount {
        ZERO_OR_ONE, ZERO_OR_MORE, ONE, ONE_OR_MORE
    }
}
