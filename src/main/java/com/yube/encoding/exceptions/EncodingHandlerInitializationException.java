package com.yube.encoding.exceptions;

public class EncodingHandlerInitializationException extends RuntimeException {

    private static final long serialVersionUID = 6056052021116300650L;

    public EncodingHandlerInitializationException() {
    }

    public EncodingHandlerInitializationException(Throwable cause) {
        super(cause);
    }

    public EncodingHandlerInitializationException(String message) {
        super(message);
    }

    public EncodingHandlerInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
