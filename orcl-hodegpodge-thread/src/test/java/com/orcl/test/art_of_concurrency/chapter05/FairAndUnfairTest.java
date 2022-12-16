package com.orcl.test.art_of_concurrency.chapter05;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-15 21:50
 * @history: 2022-12-15 21:50 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FairAndUnfairTest {

    private static ReentrantLock2 fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);

    @Test
    public void fair() {
        testLock(fairLock);
    }

    @Test
    public void unfair() {
        testLock(unfairLock);
    }

    private void testLock(ReentrantLock2 lock) {
        new Thread(new Job(lock), "1").start();
        new Thread(new Job(lock), "2").start();
        new Thread(new Job(lock), "3").start();
        new Thread(new Job(lock), "4").start();
        new Thread(new Job(lock), "5").start();
        new Thread(new Job(lock), "6").start();
    }

    static class Job implements Runnable {

        private ReentrantLock2 lock;

        public Job(ReentrantLock2 lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("level1-->Lock by [" + Thread.currentThread().getName() + "], Waiting by " + lock.getQueuedThreadNames());
                lock.lock();
                try {
                    System.out.println("level2-->Lock by [" + Thread.currentThread().getName() + "], Waiting by " + lock.getQueuedThreadNames());
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private static class ReentrantLock2 extends ReentrantLock {

        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            List<Thread> list = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(list);
            return list;
        }

        public List<String> getQueuedThreadNames() {
            Collection<Thread> threads = getQueuedThreads();
            List<String> result = new ArrayList<>();
            for (Thread thread : threads) {
                result.add(thread.getName());
            }
            return result;
        }
    }

}
