package com.miu.pmtbackendapi.exception.customexception;

public class UserDeactivedException extends Exception {
    public UserDeactivedException() {
    }

    public UserDeactivedException(String message) {
        super(message);
    }

    public UserDeactivedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDeactivedException(Throwable cause) {
        super(cause);
    }

    public UserDeactivedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
