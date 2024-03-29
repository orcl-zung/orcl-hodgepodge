package com.orcl.art_of_concurrency.chapter08;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-13 20:19
 * @history: 2022-12-13 20:19 created by orcl
 */
public class CountDownLatchTest {

    static CountDownLatch cdl = new CountDownLatch(2);

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println(1);
            System.out.println(2);
            cdl.countDown();
            cdl.countDown();
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "CountDownLatch").start();

        cdl.await();

        System.out.println(3);
    }

}
