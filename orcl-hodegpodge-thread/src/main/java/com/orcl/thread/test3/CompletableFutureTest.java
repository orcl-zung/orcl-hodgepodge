package com.orcl.thread.test3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-15 17:01
 * @history: 2022-07-15 17:01 created by orcl
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("=============>异步线程开始...");
            System.out.println("=============>异步线程为：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=============>异步线程结束...");
            return "supplierResult";
        });

        // 阻塞获取结果
        System.out.println("异步结果是:" + future.get());
        System.out.println("main结束");
    }

}
