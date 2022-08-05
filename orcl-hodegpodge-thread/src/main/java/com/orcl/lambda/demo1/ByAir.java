package com.orcl.lambda.demo1;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-05 21:23
 * @history: 2022-08-05 21:23 created by Administrator
 */
public class ByAir implements MyRunnable {

    @Override
    public void run() {
        System.out.println("在携程APP上买了票");
        System.out.println("坐飞机。。。");
    }
}
