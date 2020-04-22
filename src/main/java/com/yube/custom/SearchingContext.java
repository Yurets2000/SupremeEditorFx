package com.yube.custom;

import java.util.Set;

public interface SearchingContext<T, E extends Enum<E>> {
    Set<T> getTokens(E tokenType);
}
