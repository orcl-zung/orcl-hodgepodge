package com.orcl.art_of_concurrency.chapter04;

import com.orcl.art_of_concurrency.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-12 22:51
 * @history: 2022-12-12 22:51 created by orcl
 */
public class Deprecated {

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "PrintThread");
        printThread.setDaemon(true);
        printThread.start();
        System.out.println("PrintThread begin at " + format.format(new Date()));
        SleepUtils.second(3);

        printThread.suspend();
        System.out.println("main suspend PrintThread at " + format.format(new Date()));
        SleepUtils.second(3);

        printThread.resume();
        System.out.println("main resume PrintThread at " + format.format(new Date()));
        SleepUtils.second(3);

        printThread.stop();
        System.out.println("main stop PrintThread at " + format.format(new Date()));
        SleepUtils.second(3);

    }

    static class Runner implements Runnable {
        @Override
        public void run() {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                System.out.println(Thread.currentThread().getName() + "Run at " + format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
