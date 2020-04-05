package com.yube.configuration.exceptions;

public class IllegalConfigurationStateException extends Exception {

    private static final long serialVersionUID = 6793012834261755100L;

    public IllegalConfigurationStateException() {
    }

    public IllegalConfigurationStateException(String message) {
        super(message);
    }

    public IllegalConfigurationStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalConfigurationStateException(Throwable cause) {
        super(cause);
    }
}
