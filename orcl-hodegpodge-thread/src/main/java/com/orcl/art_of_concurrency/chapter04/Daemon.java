package com.orcl.art_of_concurrency.chapter04;

import com.orcl.art_of_concurrency.SleepUtils;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-12 21:59
 * @history: 2022-12-12 21:59 created by orcl
 */
public class Daemon {

    public static void main(String[] args) {
        Thread daemonRunner = new Thread(new DaemonRunner(), "DaemonRunner");
        daemonRunner.setDaemon(true);
        daemonRunner.start();

        SleepUtils.second(2);
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName());
                SleepUtils.second(10);
            } catch (Exception err) {
                err.printStackTrace();
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }

}
