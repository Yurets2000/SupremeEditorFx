package com.yube.configuration.exceptions;

public class TransformationException extends RuntimeException {

    private static final long serialVersionUID = 6483090674308484597L;

    public TransformationException() {
    }

    public TransformationException(Throwable cause) {
        super(cause);
    }

    public TransformationException(String message) {
        super(message);
    }

    public TransformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
