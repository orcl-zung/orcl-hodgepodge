package com.orcl.art_of_concurrency.chapter05;

import com.orcl.art_of_concurrency.SleepUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLock 死锁案例
 * @author: orcl
 * @since: 2022-12-14 19:58
 * @history: 2022-12-14 19:58 created by orcl
 */
public class DeathLockCase {

    private static Lock lockA = new ReentrantLock(true);
    private static Lock lockB = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lockA.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " lockA begin...");
                lockB.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " lockB begin...");
                } finally {
                    lockB.unlock();
                    System.out.println(Thread.currentThread().getName() + " lockB end...");
                }

            } finally {
                lockA.unlock();
                System.out.println(Thread.currentThread().getName() + " lockA end...");
            }
        }, "DeathLockThread-1").start();
        new Thread(() -> {
            lockB.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " lockB begin...");
                lockA.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " lockA begin...");
                } finally {
                    lockA.unlock();
                    System.out.println(Thread.currentThread().getName() + " lockA end...");
                }

            } finally {
                lockB.unlock();
                System.out.println(Thread.currentThread().getName() + " lockB end...");
            }
        }, "DeathLockThread-2").start();
    }

}
