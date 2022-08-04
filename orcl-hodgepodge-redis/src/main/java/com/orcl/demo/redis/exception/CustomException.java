package com.orcl.demo.redis.exception;

/**
 * @description:
 * @author: orcl
 * @since: 2022/4/25-17:11
 * @history: 2022/4/25 created by orcl
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    public CustomException(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }

}
