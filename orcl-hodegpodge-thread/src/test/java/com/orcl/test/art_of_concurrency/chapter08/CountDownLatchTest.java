package com.orcl.test.art_of_concurrency.chapter08;

import com.orcl.art_of_concurrency.SleepUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-17 22:42
 * @history: 2022-12-17 22:42 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountDownLatchTest {

    @Test
    public void test() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            list.add(i);
        }

        long l = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            SleepUtils.millisSecond(100);
        }
        System.out.println("sync execute, cost time: " + (System.currentTimeMillis() - l) + "ms");

        long l1 = System.currentTimeMillis();
        CountDownLatch cdl = new CountDownLatch(10);
        for (long i = 0; i < cdl.getCount(); i++) {
            new Thread(new CountDownLatchRunner(cdl, i, list)).start();
        }
        cdl.await();
        System.out.println("async execute, cost time: " + (System.currentTimeMillis() - l1) + "ms");

    }

    @Test
    public void test1() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(3);
        final CountDownLatch cdl = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            service.execute(() -> {
                System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                SleepUtils.second(2);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完成");
                cdl.countDown();
            });
        }
        System.out.println("主线程" + Thread.currentThread().getName() + "等待子线程执行完成。。。");
        cdl.await();
        System.out.println("主线程" + Thread.currentThread().getName() + "开始执行。。。");
    }

    @Test
    public void test2() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            service.execute(() -> {
                System.out.println("选手" + Thread.currentThread().getName() + "正在等待裁判发布口令");

                try {
                    cdOrder.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("选手" + Thread.currentThread().getName() + "已接受裁判口令");

                SleepUtils.millisSecond((long) (Math.random() * 10000));

                System.out.println("选手" + Thread.currentThread().getName() + "到达终点");

                cdAnswer.countDown();
            });
        }
        SleepUtils.millisSecond((long) (Math.random() * 10000));
        System.out.println("裁判" + Thread.currentThread().getName() + "即将发布口令");
        cdOrder.countDown();
        System.out.println("裁判" + Thread.currentThread().getName() + "已发送口令，正在等待所有选手到达终点");
        cdAnswer.await();
        System.out.println("所有选手都到达终点");
        System.out.println("裁判" + Thread.currentThread().getName() + "汇总成绩排名");
        service.shutdown();
    }

    static class CountDownLatchRunner implements Runnable {

        private CountDownLatch cdl;
        private long i;
        private List<Integer> list;

        public CountDownLatchRunner(CountDownLatch cdl, long i, List<Integer> list) {
            this.cdl = cdl;
            this.i = i;
            this.list = list;
        }

        @Override
        public void run() {
            for (long a = i * 10; a < (i + 1) * 10; a++) {
                SleepUtils.millisSecond(100);
            }
            cdl.countDown();
        }
    }


}
