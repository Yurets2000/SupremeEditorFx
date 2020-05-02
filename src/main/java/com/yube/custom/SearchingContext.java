package com.yube.custom;

import java.util.Map;

public interface SearchingContext<SC extends SearchingContext> {
    <T extends Token, E extends Enum<E>> Map<Class<E>, TokenExtractor<SC, T, E>> getExtractors();
}
