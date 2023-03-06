package com.orcl.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: orcl
 * @since: 2023-03-06 22:24
 * @history: 2023-03-06 22:24 created by orcl
 */
public class SortedTask {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(1, 1, 1000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        List<String> list = Arrays.asList("A", "B", "C");
        for (String chars : list) {
            service.execute(new Runner(chars, countDownLatch));
        }
        countDownLatch.countDown();
        service.shutdown();
        while (!service.isTerminated()) {

        }
        System.exit(0);
    }

    static class Runner implements Runnable {
        private String taskName;

        private CountDownLatch countDownLatch;

        public Runner(String taskName, CountDownLatch countDownLatch) {
            this.taskName = taskName;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println(taskName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
