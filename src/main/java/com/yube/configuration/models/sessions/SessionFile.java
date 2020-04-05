package com.yube.configuration.models.sessions;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data @ToString
public final class SessionFile {
    private final String id;
    private final String name;
    private String lang;
    private String filePath;
    private String backupFilePath;
    @NonNull
    private String encoding;
    private int displayIndex;
}
