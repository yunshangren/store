package com.liufu.store.service.ex;

public class UpdatePassWordException extends ServiceException{
    public UpdatePassWordException() {
        super();
    }

    public UpdatePassWordException(String message) {
        super(message);
    }

    public UpdatePassWordException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdatePassWordException(Throwable cause) {
        super(cause);
    }

    protected UpdatePassWordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
