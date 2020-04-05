package com.yube.configuration.models.settings;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data @ToString
public final class ShortcutConfig {
    private final String action;
    @NonNull
    private String alt;
    @NonNull
    private String code;
    @NonNull
    private String control;
    @NonNull
    private String meta;
    @NonNull
    private String shift;
    @NonNull
    private String shortcut;
}
