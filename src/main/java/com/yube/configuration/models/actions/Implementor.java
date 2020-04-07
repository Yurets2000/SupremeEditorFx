package com.yube.configuration.models.actions;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data @ToString
public final class Implementor {
    private final String type;
    private List<String> qualifiers;
    private List<String> exceptions;

    public boolean isRelatedTo(String type, String qualifier){
        if(!this.type.equals(type)) return false;
        if(qualifier == null || (qualifiers.isEmpty() && exceptions.isEmpty())) return true;
        if(qualifiers.isEmpty() && !exceptions.isEmpty()) return !exceptions.contains(qualifier);
        if(exceptions.isEmpty() && !qualifiers.isEmpty()) return qualifiers.contains(qualifier);
        return qualifiers.contains(qualifier) && !exceptions.contains(qualifier);
    }
}
