package com.liufu.store.controller.ex;

import com.liufu.store.service.ex.ServiceException;

public class AddressOverFlowException extends ServiceException {
    public AddressOverFlowException() {
        super();
    }

    public AddressOverFlowException(String message) {
        super(message);
    }

    public AddressOverFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressOverFlowException(Throwable cause) {
        super(cause);
    }

    protected AddressOverFlowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
