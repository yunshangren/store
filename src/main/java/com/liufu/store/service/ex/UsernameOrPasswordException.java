package com.liufu.store.service.ex;

public class UsernameOrPasswordException extends ServiceException{
    public UsernameOrPasswordException() {
        super();
    }

    public UsernameOrPasswordException(String message) {
        super(message);
    }

    public UsernameOrPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameOrPasswordException(Throwable cause) {
        super(cause);
    }

    protected UsernameOrPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
