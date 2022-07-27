package com.orcl.thread.test1;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-10 11:44
 * @history: 2022-07-10 11:44 created by orcl
 */
public class ThreadCreateTest {

    public static void main(String[] args) {

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "线程启动");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "线程执行完毕");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t.start();


    }

}
