package com.orcl.thread.test4;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: orcl
 * @since: 2022-09-07 10:37
 * @history: 2022-09-07 10:37 created by orcl
 */
public class DaemonThreadTest {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true) {

            }
        }, "t1");

        t1.setDaemon(true);
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t2.start();

        System.out.println(Thread.currentThread().getName() + "\t" + "主线程 END");

        // ACID： Atomic原子性、Consistency一致性、Isolation隔离性、Durability持久性
        // CAP理论：Consistency一致性、Availability可用性、Partition tolerance分区容错性

    }

}
