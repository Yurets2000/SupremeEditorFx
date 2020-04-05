package com.yube.controls.exceptions;

public class ControlHandlerInitializationException extends Exception {

    private static final long serialVersionUID = -4393805990281976310L;

    public ControlHandlerInitializationException() {
    }

    public ControlHandlerInitializationException(String message) {
        super(message);
    }

    public ControlHandlerInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControlHandlerInitializationException(Throwable cause) {
        super(cause);
    }
}
