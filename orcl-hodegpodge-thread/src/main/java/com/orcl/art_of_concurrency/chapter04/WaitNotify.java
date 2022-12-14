package com.orcl.art_of_concurrency.chapter04;

import com.orcl.art_of_concurrency.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-13 14:48
 * @history: 2022-12-13 14:48 created by orcl
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.setPriority(Thread.MAX_PRIORITY);
        waitThread.start();
        SleepUtils.second(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            // 加锁，拥有 lock 的 Monitor
            synchronized (lock) {
                // 当条件不满足时，继续 wait，同时释放 lock 锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 条件满足时，完成工作
                System.out.println(Thread.currentThread() + "flag is false. running@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            // 加锁，拥有 lock 的 Monitor
            synchronized (lock) {
                // 获取 lock 的锁，然后进行通知，通知时不会释放 lock 的锁
                // 直到当前线程释放了 lock 后，WaitThread 才能从 wait 方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(60);
                lock.notifyAll();
                flag = false;
                SleepUtils.second(60);
            }

            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }

}
