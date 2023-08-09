package com.orcl.springboot.event;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lea
 * @description
 * @history 2023-06-12 23:33 created by lea
 * @since 2023-06-12 23:33
 */
@Slf4j
@Component
public class MyEventListener2 implements ApplicationListener<MyEvent<Task>> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(MyEvent event) {
        TimeUnit.SECONDS.sleep(1);

        Task task = (Task) event.getTask();
        log.info("监听器 2 接收任务：{}", JSON.toJSONString(task));
        task.setFinish(true);
        log.info("监听器 2 此时完成了任务");
    }

    @EventListener
    public void someMethod(MyEvent<Task> event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("监听器 @EventListener 2 收到={}", event.getTask());
    }

}
