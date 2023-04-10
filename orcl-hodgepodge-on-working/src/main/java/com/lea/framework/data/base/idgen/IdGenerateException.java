package com.lea.framework.data.base.idgen;

/**
 * @author lea
 * @description
 * @history 2023-04-07 18:15 created by lea
 * @since 2023-04-07 18:15
 */
public class IdGenerateException extends RuntimeException{

    public IdGenerateException(String message) {
        super(message);
    }

    public IdGenerateException(String message, Throwable cause) {
        super(message, cause);
    }
}
