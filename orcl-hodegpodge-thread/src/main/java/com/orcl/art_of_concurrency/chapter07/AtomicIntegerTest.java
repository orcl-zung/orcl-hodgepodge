package com.orcl.art_of_concurrency.chapter07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-17 18:38
 * @history: 2022-12-17 18:38 created by orcl
 */
public class AtomicIntegerTest {

    static AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.getAndSet(10));
        System.out.println(ai.compareAndSet(10, 5));
        System.out.println(ai.get());
    }

}
