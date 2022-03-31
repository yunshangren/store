package com.liufu.store.service.ex;

public class UpdateUserDataException extends ServiceException{
    public UpdateUserDataException() {
        super();
    }

    public UpdateUserDataException(String message) {
        super(message);
    }

    public UpdateUserDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateUserDataException(Throwable cause) {
        super(cause);
    }

    protected UpdateUserDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
