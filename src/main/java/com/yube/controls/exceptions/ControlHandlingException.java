package com.yube.controls.exceptions;

public class ControlHandlingException extends RuntimeException {

    private static final long serialVersionUID = -3284598524584481210L;

    public ControlHandlingException() {
    }

    public ControlHandlingException(String message) {
        super(message);
    }

    public ControlHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControlHandlingException(Throwable cause) {
        super(cause);
    }
}
