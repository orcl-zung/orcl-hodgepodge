package com.orcl.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description: A B C 三个线程顺序执行（使用 wait/notify 实现）
 * @author: orcl
 * @since: 2023-03-07 15:31
 * @history: 2023-03-07 15:31 created by orcl
 */
public class SortThread1 {

    public static void main(String[] args) {

        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(() -> {
            synchronized (obj1) {
                try {
                    obj1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }, "C").start();

        new Thread(() -> {
            synchronized (obj2) {
                try {
                    obj2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                obj1.notify();
            }

        }, "B").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName());
                obj2.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

    }

}
