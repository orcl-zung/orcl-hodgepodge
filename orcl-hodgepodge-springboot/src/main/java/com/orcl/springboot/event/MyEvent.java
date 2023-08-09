package com.orcl.springboot.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author lea
 * @description 事件类需要继承org.springframework.context.ApplicationEvent，这样发布的事件才能被Spring所识别
 * @history 2023-06-12 23:31 created by lea
 * @since 2023-06-12 23:31
 */
public class MyEvent<T> extends ApplicationEvent {

    private T task;

    public MyEvent(T task) {
        super(task);
        this.task = task;
    }

    public T getTask() {
        return task;
    }
}
