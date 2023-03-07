package com.orcl.thread;

import java.util.Arrays;
import java.util.List;

/**
 * @description: A B C 三个线程顺序执行（使用 join 实现）
 * @author: orcl
 * @since: 2023-03-07 15:13
 * @history: 2023-03-07 15:13 created by orcl
 */
public class SortThread {

    public static void main(String[] args) {
        Thread curThread = Thread.currentThread();
        List<String> threadNames = Arrays.asList("A", "B", "C", "D");
        for (String threadName : threadNames) {
            Thread thread = new Thread(new SortedRunner(curThread), threadName);
            thread.start();
            curThread = thread;
        }
    }

    static class SortedRunner implements Runnable {

        private Thread thread;

        public SortedRunner(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                System.out.println(thread.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
