package com.orcl.art_of_concurrency.chapter07;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-17 20:51
 * @history: 2022-12-17 20:51 created by orcl
 */
public class AtomicIntegerArrayTest {

    static int[] value = new int[]{1, 2};

    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.addAndGet(0, 10);
        ai.getAndIncrement(1);

        System.out.println(ai);
    }

}
