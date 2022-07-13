package com.orcl.thread.test1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-10 14:40
 * @history: 2022-07-10 14:40 created by orcl
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {

            System.out.println(Thread.currentThread().getName() + "dafdasfdasfdas");

            return "this is result";

        });

        Thread t = new Thread(futureTask);
        t.start();

        String sth = futureTask.get();
        System.out.println(sth);


    }

}
