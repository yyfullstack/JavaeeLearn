package com.login.exception;

/**
 * Created by yong on 2016/10/31 0031.
 */
public class UserExistException extends Exception {
    public UserExistException() {
        super();
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }
}
