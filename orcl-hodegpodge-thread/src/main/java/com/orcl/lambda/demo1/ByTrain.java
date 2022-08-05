package com.orcl.lambda.demo1;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-05 21:23
 * @history: 2022-08-05 21:23 created by Administrator
 */
public class ByTrain implements MyRunnable{

    @Override
    public void run() {
        System.out.println("去12306上买了票");
        System.out.println("坐火车。。。");
    }

}
