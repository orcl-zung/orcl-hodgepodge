package com.orcl.thread.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-11 11:39
 * @history: 2022-07-11 11:39 created by orcl
 */
@Slf4j
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPool threadPoolExecutor = new ThreadPool(1, 2, 1,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));

        threadPoolExecutor.execute(() -> {
            log.info("{}:执行第1个任务...", Thread.currentThread().getName());
            sleep(10);
        });

        sleep(1);

        threadPoolExecutor.execute(() -> {
            log.info("{}:执行第2个任务...", Thread.currentThread().getName());
            sleep(10);

        });

        sleep(1);

        threadPoolExecutor.execute(() -> {
            log.info("{}:执行第3个任务...", Thread.currentThread().getName());
            sleep(10);
        });

        sleep(1);

        threadPoolExecutor.execute(() -> {
            log.info("{}:执行第4个任务...", Thread.currentThread().getName());
            sleep(10);
        });

        sleep(1);

        log.info("main结束");

    }

    private static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
