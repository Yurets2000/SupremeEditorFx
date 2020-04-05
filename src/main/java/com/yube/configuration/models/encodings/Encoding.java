package com.yube.configuration.models.encodings;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data @ToString
public class Encoding {
    private final String name;
    @NonNull
    private String type;
    private int length;
}
