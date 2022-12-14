package com.orcl.art_of_concurrency.chapter05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-14 19:50
 * @history: 2022-12-14 19:50 created by orcl
 */
public class LockUseCase {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        lock.lock();
        try {

        } finally {
            lock.unlock();
        }

    }

}
