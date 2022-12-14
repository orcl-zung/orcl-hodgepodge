package com.orcl.art_of_concurrency.chapter04;

import com.orcl.art_of_concurrency.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-13 15:20
 * @history: 2022-12-13 15:20 created by orcl
 */
public class CusWaitNotify {

    public static void main(String[] args) {
        new Thread(new CusWait(), "CusWaitThread").start();
        SleepUtils.second(1);
        new Thread(new CusNotify(), "CusNotifyThread").start();
    }

    static boolean flag = true;
    static Object lock = new Object();

    static class CusWait implements Runnable {
        @Override
        public void run() {
            while (flag) {
                try {
                    System.out.println(Thread.currentThread().getName() + "flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }

    static class CusNotify implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            lock.notifyAll();
            flag = false;
            SleepUtils.second(5);

            System.out.println(Thread.currentThread().getName() + " hold lock again. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            SleepUtils.second(5);
        }
    }

}
