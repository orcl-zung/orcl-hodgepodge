package com.orcl.thread.test2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-11 10:20
 * @history: 2022-07-11 10:20 created by orcl
 */
public class SimpleThreadPoolTest {

    public static void main(String[] args) {

        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(2, new ArrayBlockingQueue<>(2));

        simpleThreadPool.execute(() -> {
            System.out.println("第1个任务开始");
            sleep(3);
            System.out.println("第1个任务结束");
        });
        simpleThreadPool.execute(() -> {
            System.out.println("第2个任务开始");
            sleep(4);
            System.out.println("第2个任务结束");
        });
        simpleThreadPool.execute(() -> {
            System.out.println("第3个任务开始");
            sleep(5);
            System.out.println("第3个任务结束");
        });
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
