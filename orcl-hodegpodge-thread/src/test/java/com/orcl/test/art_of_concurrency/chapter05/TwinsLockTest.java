package com.orcl.test.art_of_concurrency.chapter05;

import com.orcl.art_of_concurrency.SleepUtils;
import com.orcl.art_of_concurrency.chapter05.TwinsLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.locks.Lock;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-15 20:07
 * @history: 2022-12-15 20:07 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TwinsLockTest {

    @Test
    public void test() {
        final Lock lock = new TwinsLock(2);

        Runnable runner = () -> {
            while (true) {
                lock.lock();
                try {
                    SleepUtils.second(1);
                    System.out.println(Thread.currentThread());
                    SleepUtils.second(1);
                } finally {
                    lock.unlock();
                }
            }
        };

        // 启动 10 个线程
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runner, "TwinsLockThread-" + i);
            thread.setDaemon(true);
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }

    }

}
