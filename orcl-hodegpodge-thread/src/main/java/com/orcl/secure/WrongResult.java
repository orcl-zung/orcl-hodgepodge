package com.orcl.secure;

/**
 * @author lea
 * @description
 * @history 2023-08-09 22:55 created by lea
 * @since 2023-08-09 22:55
 */
public class WrongResult {

    volatile static int i;

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i1 = 0; i1 < 10000; i1++) {
                i++;
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        Thread thread1 = new Thread(r);
        thread1.start();

        thread.join();
        thread1.join();
        System.out.println(i);
    }

}
