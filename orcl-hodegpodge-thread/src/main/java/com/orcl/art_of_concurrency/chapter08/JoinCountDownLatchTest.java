package com.orcl.art_of_concurrency.chapter08;

import com.orcl.art_of_concurrency.SleepUtils;
import com.orcl.art_of_concurrency.chapter04.Profiler;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-13 20:13
 * @history: 2022-12-13 20:13 created by orcl
 */
public class JoinCountDownLatchTest {

    public static void main(String[] args) throws Exception {
        Profiler.begin();
        Thread parser1 = new Thread(() -> SleepUtils.second(4));

        Thread parser2 = new Thread(() -> {
            SleepUtils.second(2);
            System.out.println("parser2 finish");
        });

        parser1.start();
        parser2.start();

        parser1.join();
        parser2.join();

        long cost = Profiler.end();

        System.out.println("all parser finish, cost " + cost + " ms");

    }

}
