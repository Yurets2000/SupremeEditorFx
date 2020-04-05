package com.yube.configuration.models.styling;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data @ToString
public final class LexerStyle {
    private final String name;
    @NonNull
    private String desc;
    @NonNull
    private String elements;
    @NonNull
    private String pattern;
    @NonNull
    private String path;
}
