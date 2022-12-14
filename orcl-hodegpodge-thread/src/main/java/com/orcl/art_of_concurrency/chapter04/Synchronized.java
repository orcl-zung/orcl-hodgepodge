package com.orcl.art_of_concurrency.chapter04;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-13 11:54
 * @history: 2022-12-13 11:54 created by orcl
 */
public class Synchronized {

    public static void main(String[] args) {
        synchronized (Synchronized.class) {

        }
        m();
    }

    public static synchronized void m() {

    }

}
