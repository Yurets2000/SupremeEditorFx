package com.yube.custom.searcher;

import lombok.Data;

import java.util.Objects;

@Data
public class CodeToken implements Token {
    private final int column, row;
    private final String selected;
    private final String value;
    private final String line;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeToken codeToken = (CodeToken) o;
        return Objects.equals(value, codeToken.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
