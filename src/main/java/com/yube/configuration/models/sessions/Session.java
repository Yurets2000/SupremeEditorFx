package com.yube.configuration.models.sessions;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data @ToString
public final class Session {
    private final String name;
    @NonNull
    private String active;
    private long creationTime;
}
