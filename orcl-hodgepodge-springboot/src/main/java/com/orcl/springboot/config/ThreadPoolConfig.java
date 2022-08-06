package com.orcl.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-06 11:33
 * @history: 2022-08-06 11:33 created by Administrator
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("taskServiceExecutor")
    public ThreadPoolTaskExecutor taskServiceExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setQueueCapacity(500);
        taskExecutor.setKeepAliveSeconds(1000);
        taskExecutor.setThreadNamePrefix("task-service-");
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

}
