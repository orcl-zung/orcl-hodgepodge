package com.orcl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-16 10:30
 * @history: 2022-07-16 10:30 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompletableFutureTest {

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Test
    public void testCallBack() throws InterruptedException, ExecutionException {
        // 提交一个任务，返回CompletableFuture（注意，并不是把CompletableFuture提交到线程池，它没有实现Runnable）
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("=============>异步线程开始...");
            System.out.println("=============>异步线程为：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=============>异步线程结束...");
            return "supplierResult";
        })
                // 异步回调：上面的Supplier#get()返回结果后，异步线程会回调BiConsumer#accept()
                .whenComplete((s, throwable) -> {
                    System.out.println("=============>异步任务结束回调...");
                    System.out.println("=============>回调线程为：" + Thread.currentThread().getName());
                });

        // CompletableFuture的异步线程是守护线程，一旦main结束就没了，为了看到打印结果，需要让main休眠一会儿
        System.out.println("main结束");
        TimeUnit.SECONDS.sleep(15);

    }

    @Test
    public void testCallBack1() throws InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("==========>异步线程开始。。。");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("==========>completableFuture1任务结束。。。");
            System.out.println("==========>执行completableFuture1的线程为：" + Thread.currentThread().getName());
            return "supplierResult";
        });
        System.out.println("completableFuture1：" + completableFuture1);

        CompletableFuture<String> completableFuture2 = completableFuture1.thenApply(s -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("==========>completableFuture2任务结束 result = " + s);
            System.out.println("==========>执行completableFuture2的线程为：" + Thread.currentThread().getName());
            return s;
        });
        System.out.println("completableFuture2：" + completableFuture2);

        CompletableFuture<String> completableFuture3 = completableFuture2.thenApply(s -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("==========>completableFuture3任务结束 result = " + s);
            System.out.println("==========>执行completableFuture3的线程为：" + Thread.currentThread().getName());
            return s;
        });
        System.out.println("completableFuture3：" + completableFuture3);

        System.out.println("主线程结束");
        TimeUnit.SECONDS.sleep(40);

    }

}