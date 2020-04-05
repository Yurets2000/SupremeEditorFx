package com.yube.configuration.models.settings;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.util.HashMap;

@Data @ToString
public final class GuiConfig {
    private final String name;
    @NonNull
    private HashMap<String, String> additionalAttributes;
}
