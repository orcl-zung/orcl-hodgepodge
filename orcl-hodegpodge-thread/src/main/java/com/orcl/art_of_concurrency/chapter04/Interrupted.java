package com.orcl.art_of_concurrency.chapter04;

import com.orcl.art_of_concurrency.SleepUtils;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-12 22:25
 * @history: 2022-12-12 22:25 created by orcl
 */
public class Interrupted {

    public static void main(String[] args) {
        // 不停地尝试休眠
        Thread sleepRunner = new Thread(new SleepRunner(), "SleepRunner");
        sleepRunner.setDaemon(true);

        // 不停地尝试休眠
        Thread busyRunner = new Thread(new BusyRunner(), "BusyRunner");
        busyRunner.setDaemon(true);

        sleepRunner.start();
        busyRunner.start();

        // 休眠 5 秒，让 sleepRunner 和 busyRunner 充分运行
        SleepUtils.second(5);
        sleepRunner.interrupt();
        busyRunner.interrupt();

        System.out.println("SleepThread interrupted is " + sleepRunner.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyRunner.isInterrupted());

        // 防止 sleepRunner 和 busyRunner 立刻退出
        SleepUtils.second(2);

    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
