package com.yube.encoding.hierarchy;

public interface EncodingHandler {
    byte[] encode(String text);
    String decode(byte[] bytes);
}
