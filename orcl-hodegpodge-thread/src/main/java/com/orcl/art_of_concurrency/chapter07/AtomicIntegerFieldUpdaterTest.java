package com.orcl.art_of_concurrency.chapter07;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-17 21:01
 * @history: 2022-12-17 21:01 created by orcl
 */
public class AtomicIntegerFieldUpdaterTest {

    static AtomicIntegerFieldUpdater<User> afu = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public static void main(String[] args) {
        afu.set(new User("james", 22), 23);
        System.out.println(afu);
        User kobe = new User("kobe", 38);
        afu.compareAndSet(kobe, 1, 2);
        System.out.println(afu);
    }


    @Data
    @AllArgsConstructor
    static class User {
        private String name;
        public volatile int age;
    }

}
