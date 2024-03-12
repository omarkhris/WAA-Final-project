package com.miu.pmtbackendapi.exception.customexception;

public class ItemNotFoundException extends Exception {

    String message;

    public ItemNotFoundException() {
    }

    public ItemNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }

    public ItemNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
