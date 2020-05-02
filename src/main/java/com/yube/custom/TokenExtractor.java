package com.yube.custom;

import java.util.List;

public interface TokenExtractor<SC extends SearchingContext, T extends Token, E extends Enum<E>> {
    List<T> extractTokens(SC context, E tokenType, String filter);
}
