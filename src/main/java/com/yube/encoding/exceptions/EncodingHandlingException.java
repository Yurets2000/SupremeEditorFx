package com.yube.encoding.exceptions;

public class EncodingHandlingException extends RuntimeException {

    private static final long serialVersionUID = -9064359767621072792L;

    public EncodingHandlingException() {
    }

    public EncodingHandlingException(String message) {
        super(message);
    }

    public EncodingHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncodingHandlingException(Throwable cause) {
        super(cause);
    }
}
