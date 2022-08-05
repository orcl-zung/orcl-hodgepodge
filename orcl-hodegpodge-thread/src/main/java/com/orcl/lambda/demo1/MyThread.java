package com.orcl.lambda.demo1;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-05 21:22
 * @history: 2022-08-05 21:22 created by Administrator
 */
public class MyThread {

    private MyRunnable target;

    public MyThread(MyRunnable target) {
        this.target = target;
    }

    public void run() {
        System.out.println("在12306上买的大巴票");
        System.out.println("坐大巴。。。");
    }

    public void start() {
        if (null != target) target.run();
        else this.run();
    }
}
