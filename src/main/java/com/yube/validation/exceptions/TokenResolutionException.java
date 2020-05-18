package com.yube.validation.exceptions;

public class TokenResolutionException extends RuntimeException {

    private static final long serialVersionUID = 4044291242060440753L;

    public TokenResolutionException() {
    }

    public TokenResolutionException(String message) {
        super(message);
    }

    public TokenResolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenResolutionException(Throwable cause) {
        super(cause);
    }
}
