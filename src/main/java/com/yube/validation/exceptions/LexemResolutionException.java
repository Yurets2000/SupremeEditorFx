package com.yube.validation.exceptions;

public class LexemResolutionException extends RuntimeException {

    private static final long serialVersionUID = 5211705559534274553L;

    public LexemResolutionException() {
    }

    public LexemResolutionException(String message) {
        super(message);
    }

    public LexemResolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public LexemResolutionException(Throwable cause) {
        super(cause);
    }
}
