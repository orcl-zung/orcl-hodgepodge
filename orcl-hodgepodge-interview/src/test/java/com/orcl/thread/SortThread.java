package com.orcl.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: orcl
 * @since: 2023-03-07 15:24
 * @history: 2023-03-07 15:24 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SortThread {

    @Test
    public void test_sort1() {
        Thread curThread = Thread.currentThread();
        List<String> threadNames = Arrays.asList("A", "B", "C", "D");
        for (String threadName : threadNames) {
            Thread thread = new Thread(new SortedRunner(curThread), threadName);
            thread.start();
            curThread = thread;
        }
    }

    class SortedRunner implements Runnable {

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
