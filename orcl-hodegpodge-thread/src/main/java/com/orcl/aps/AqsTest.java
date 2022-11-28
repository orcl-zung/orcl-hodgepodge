package com.orcl.aps;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: AQS 测试类
 * @author: orcl
 * @since: 2022-09-15 04:59
 * @history: 2022-09-15 04:59 created by orcl
 */
public class AqsTest {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("Thread A coming...");
                TimeUnit.MINUTES.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("Thread B coming...");
            } finally {
                reentrantLock.unlock();
            }
        }, "B").start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("Thread C coming...");
            } finally {
                reentrantLock.unlock();
            }
        }, "C").start();
    }

}
