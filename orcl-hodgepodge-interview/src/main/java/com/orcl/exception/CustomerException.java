package com.orcl.exception;

/**
 * @author lea
 * @description
 * @history 2023-05-25 23:16 created by lea
 * @since 2023-05-25 23:16
 */
public class CustomerException extends RuntimeException {

    public CustomerException() {
        super();
    }

    public CustomerException(String message) {
        super(message);
    }
}
