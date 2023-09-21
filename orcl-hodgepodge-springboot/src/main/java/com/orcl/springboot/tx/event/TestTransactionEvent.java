package com.orcl.springboot.tx.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author lea
 * @description
 * @history 2023-09-21 22:59 created by lea
 * @since 2023-09-21 22:59
 */
public class TestTransactionEvent extends ApplicationEvent {

    private final String payload;

    public TestTransactionEvent(Object source, String payload) {
        super(source);
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}
