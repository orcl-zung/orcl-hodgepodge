package com.orcl.mybatis.proxy.demo1;

/**
 * @author lea
 * @description
 * @history 2023-05-07 18:05 created by lea
 * @since 2023-05-07 18:05
 */
public class CalculatorProxy implements Calculator{

    private Calculator target;

    public CalculatorProxy(Calculator target) {
        this.target = target;
    }

    @Override
    public int add(int a, int b) {
        System.out.println("Method add begin...");
        int result = target.add(a, b);
        System.out.println("Method add end...");
        return result;
    }

    @Override
    public int sub(int a, int b) {
        System.out.println("Method sub begin...");
        int result = target.sub(a, b);
        System.out.println("Method sub end...");
        return result;
    }
}
