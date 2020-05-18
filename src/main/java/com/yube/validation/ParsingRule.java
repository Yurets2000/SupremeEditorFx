package com.yube.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ParsingRule extends Rule {

    private List<? extends Rule> ruleComponents;
    private RuleType type;

    public ParsingRule(String value) {
        super(value);
    }

    public enum RuleType {
        GROUP_ZERO_OR_ONE, GROUP_ZERO_OR_MORE, GROUP_ONE, GROUP_ONE_OR_MORE, OR, ONE, AND
    }
}
