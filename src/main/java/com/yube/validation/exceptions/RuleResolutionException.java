package com.yube.validation.exceptions;

public class RuleResolutionException extends RuntimeException {

    private static final long serialVersionUID = 7475705309626340838L;

    public RuleResolutionException() {
    }

    public RuleResolutionException(String message) {
        super(message);
    }

    public RuleResolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleResolutionException(Throwable cause) {
        super(cause);
    }
}
