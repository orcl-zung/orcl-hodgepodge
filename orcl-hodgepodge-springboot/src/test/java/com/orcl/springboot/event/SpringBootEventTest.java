package com.orcl.springboot.event;

import com.orcl.springboot.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author lea
 * @description
 * @history 2023-06-12 23:25 created by lea
 * @since 2023-06-12 23:25
 */
@Slf4j
public class SpringBootEventTest extends ApplicationTest {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void testSpringBootEvent() throws InterruptedException {
        Task task = new Task();
        task.setId(1L);
        task.setTaskName("test task");
        task.setTaskContext("task content");
        task.setFinish(false);
        MyEvent<Task> event = new MyEvent<>(task);

        log.info("--------------- begin publish task ----------------");
        applicationEventPublisher.publishEvent(event);
        log.info("--------------- end publish task ----------------");
        TimeUnit.SECONDS.sleep(10);
    }

}
