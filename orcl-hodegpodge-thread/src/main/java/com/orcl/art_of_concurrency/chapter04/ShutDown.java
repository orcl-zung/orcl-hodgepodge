package com.orcl.art_of_concurrency.chapter04;

import com.orcl.art_of_concurrency.SleepUtils;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-12 23:37
 * @history: 2022-12-12 23:37 created by orcl
 */
public class ShutDown {

    public static void main(String[] args) {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread-one");
        countThread.start();
        SleepUtils.second(1);

        countThread.interrupt();
        System.out.println("CountThread-one is interrupted");

        Runner two = new Runner();
        countThread = new Thread(two, "CountThread-two");
        countThread.start();
        SleepUtils.second(1);

        two.cancel();
    }

    private static class Runner implements Runnable {

        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(Thread.currentThread().getName() + "'s Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
