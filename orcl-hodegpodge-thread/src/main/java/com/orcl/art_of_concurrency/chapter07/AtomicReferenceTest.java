package com.orcl.art_of_concurrency.chapter07;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-17 20:56
 * @history: 2022-12-17 20:56 created by orcl
 */
public class AtomicReferenceTest {

    static User kobe = new User("kobe", 23);

    static AtomicReference<User> aru = new AtomicReference<>(kobe);

    public static void main(String[] args) {
        System.out.println(aru);

        User james = new User("james", 33);

        aru.compareAndSet(kobe, james);

        System.out.println(aru);
    }


    @Data
    @AllArgsConstructor
    static class User {
        private String name;
        private int age;
    }

}
