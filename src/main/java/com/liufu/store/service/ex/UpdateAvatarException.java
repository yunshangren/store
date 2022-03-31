package com.liufu.store.service.ex;

public class UpdateAvatarException extends ServiceException{
    public UpdateAvatarException() {
        super();
    }

    public UpdateAvatarException(String message) {
        super(message);
    }

    public UpdateAvatarException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateAvatarException(Throwable cause) {
        super(cause);
    }

    protected UpdateAvatarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
