package com.yube.encoding.exceptions;

public class UnsupportedEncodingException extends IllegalArgumentException {

    private static final long serialVersionUID = -4919138007931347821L;

    public UnsupportedEncodingException() {
    }

    public UnsupportedEncodingException(String s) {
        super(s);
    }

    public UnsupportedEncodingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedEncodingException(Throwable cause) {
        super(cause);
    }
}
