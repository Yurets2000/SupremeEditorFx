package com.yube.configuration.models.encodings;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data @ToString
public final class Mapping {
    @NonNull
    private String type;
    @NonNull
    private String path;
}
