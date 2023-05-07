package com.orcl.mybatis.proxy.demo1;

/**
 * @author lea
 * @description
 * @history 2023-05-07 18:04 created by lea
 * @since 2023-05-07 18:04
 */
public class CalculatorImpl implements Calculator{

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }
}
