package com.liufu.store.service.ex;

public class AddAddressException extends ServiceException{
    public AddAddressException() {
        super();
    }

    public AddAddressException(String message) {
        super(message);
    }

    public AddAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddAddressException(Throwable cause) {
        super(cause);
    }

    protected AddAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
