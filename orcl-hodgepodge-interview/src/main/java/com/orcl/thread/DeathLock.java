package com.orcl.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description: 死锁场景
 * @author: orcl
 * @since: 2023-03-07 14:45
 * @history: 2023-03-07 14:45 created by orcl
 */
public class DeathLock {

    /*
        死锁的四个必要条件：
            1. 互斥条件：该资源任意一个时刻只由一个线程占用
            2. 请求与保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放
            3. 不剥夺条件：线程已获得资源在未使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源
            4. 循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系

        避免死锁也是从这四个条件去思考：
            1. 破坏请求与保持条件：一次性申请所有的资源
            2. 破坏不剥夺条件：占用部分资源的线程在请求剩下资源时，如果获取不到，则主动释放已占有的资源
            3. 破坏循环等待条件：通过按序申请资源来预防。按照某一特定顺序来申请资源，释放资源则反序释放。
     */

    private static volatile Object lockObj1 = new Object();
    private static volatile Object lockObj2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lockObj1) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("同步代码块 A-1");
                    synchronized (lockObj2) {
                        System.out.println("同步代码块 A-2");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "A").start();

        new Thread(() -> {
            synchronized (lockObj2) {
                System.out.println("同步代码块 B-1");
                synchronized (lockObj1) {
                    System.out.println("同步代码块 B-2");
                }
            }
        }, "B").start();
    }

}
