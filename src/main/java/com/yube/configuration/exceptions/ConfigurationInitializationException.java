package com.yube.configuration.exceptions;

public class ConfigurationInitializationException extends Exception {

    private static final long serialVersionUID = 6958161299567286694L;

    public ConfigurationInitializationException() {
    }

    public ConfigurationInitializationException(String message) {
        super(message);
    }

    public ConfigurationInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationInitializationException(Throwable cause) {
        super(cause);
    }
}
