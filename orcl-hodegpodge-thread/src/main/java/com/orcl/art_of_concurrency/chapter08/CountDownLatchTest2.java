package com.orcl.art_of_concurrency.chapter08;

import com.orcl.art_of_concurrency.chapter04.Profiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-17 21:30
 * @history: 2022-12-17 21:30 created by orcl
 */
public class CountDownLatchTest2 {

    static int threadCount;

    static List<String> beginTimeList = Collections.synchronizedList(new ArrayList<>());

    static CountDownLatch cdl = new CountDownLatch(threadCount);

    public CountDownLatchTest2(int count) {
        threadCount = count;
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        new CountDownLatchTest2(10);
        for (int i = 0; i < threadCount; i++) {
            new Thread(new CountDownLatchRunner(), "CountDownLatchRunner--" + i).start();
        }
//        cdl.await();
        System.out.println("10 thread cost " + Profiler.end() + " ms");

        for (String s : beginTimeList) {
            System.out.println(s);
        }

    }

    static class CountDownLatchRunner implements Runnable {

        @Override
        public void run() {
            cdl.countDown();
            try {
                System.out.println(Thread.currentThread().getName());
                cdl.await();
                beginTimeList.add(String.valueOf(System.currentTimeMillis()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
