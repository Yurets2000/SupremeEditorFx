package com.yube.configuration.models.actions;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data @ToString
public class Implementor {
    private final String type;
    private List<String> qualifiers;
    private List<String> exceptions;
}
