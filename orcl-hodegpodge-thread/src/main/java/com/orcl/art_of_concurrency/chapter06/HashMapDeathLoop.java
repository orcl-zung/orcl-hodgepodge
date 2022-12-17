package com.orcl.art_of_concurrency.chapter06;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-16 19:25
 * @history: 2022-12-16 19:25 created by orcl
 */
public class HashMapDeathLoop {

    public static void main(String[] args) throws InterruptedException {
        final Map<String, String> map = new HashMap<>();

        Thread t = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> map.put(UUID.randomUUID().toString(), ""), "ftf" + i).start();
            }
        }, "ftf");
        t.start();
        t.join();
        System.out.println(map.size());
    }

}
