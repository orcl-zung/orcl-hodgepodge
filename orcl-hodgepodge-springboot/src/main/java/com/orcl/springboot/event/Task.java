package com.orcl.springboot.event;

import lombok.Data;

/**
 * @author lea
 * @description 任务就是一个普通的类，用来保存你要发布事件的内容。这个没有特殊限制，可以根据自己业务随意设置。
 * @history 2023-06-12 23:28 created by lea
 * @since 2023-06-12 23:28
 */
@Data
public class Task {

    private Long id;

    private String taskName;

    private String taskContext;

    private boolean finish;

}
