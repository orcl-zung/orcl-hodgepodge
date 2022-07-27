package com.orcl.reflection.junit;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-19 11:08
 * @history: 2022-07-19 11:08 created by orcl
 */
public class EmpDao {

    @MyBefore
    public void init() {
        System.out.println("初始化。。。");
    }

    @MyAfter
    public void destroy() {
        System.out.println("销毁。。。");
    }

    @MyTest
    public void testSave() {
        System.out.println("save...");
    }

    @MyTest
    public void testDelete() {
        System.out.println("delete...");
    }

}
