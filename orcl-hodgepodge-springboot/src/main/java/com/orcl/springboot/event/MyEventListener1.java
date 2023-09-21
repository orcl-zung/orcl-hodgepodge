package com.orcl.springboot.event;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
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
public class MyEventListener1 implements ApplicationListener<MyEvent<Task>> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(MyEvent event) {
        TimeUnit.SECONDS.sleep(5);

        Task task = (Task) event.getTask();
        log.info("监听器 1 接收任务：{}", JSON.toJSONString(task));
        task.setFinish(true);
        log.info("监听器 1 此时完成了任务");
    }

}
