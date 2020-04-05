package com.yube.configuration.models.actions;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data @ToString
public class Action {
    private final String name;
    private String desc;
    private List<Definer> definers;
    private List<Implementor> implementors;
}
