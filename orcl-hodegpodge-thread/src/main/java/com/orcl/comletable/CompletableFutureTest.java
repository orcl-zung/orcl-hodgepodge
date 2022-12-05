package com.orcl.comletable;

import java.util.concurrent.*;

/**
 * @description:
 * @author: orcl
 * @since: 2022-11-28 11:35
 * @history: 2022-11-28 11:35 created by orcl
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        supplyAsync();
    }

    private static void runAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(future.get());
    }

    private static void runAsyncWithThreadPool() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executorService);
        System.out.println(future.get());
    }

    private static void supplyAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync";
        });
        System.out.println(future.get());
    }

}
