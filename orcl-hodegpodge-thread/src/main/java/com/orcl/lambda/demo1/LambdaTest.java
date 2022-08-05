package com.orcl.lambda.demo1;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-05 21:30
 * @history: 2022-08-05 21:30 created by Administrator
 */
public class LambdaTest {

    public static void main(String[] args) {

        // 使用策略模式，类似线程类 Thread 和 Runnable 的关系
        ByAir byAir = new ByAir();
        ByTrain byTrain = new ByTrain();
        MyThread myThread = new MyThread(byAir);
        myThread.start();

        MyThread myThread1 = new MyThread(byTrain);
        myThread1.start();

        System.out.println("-------------------------------------------");

        // 使用 lambda 进行替换
        new MyThread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println("在携程APP上买了票");
                System.out.println("坐飞机。。。");
            }
        }).start();

        new MyThread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println("去12306上买了票");
                System.out.println("坐火车。。。");
            }
        }).start();
    }

}
