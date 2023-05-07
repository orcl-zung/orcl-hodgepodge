package com.orcl.thread;

/**
 * @description:
 * @author: orcl
 * @since: 2023-03-09 19:35
 * @history: 2023-03-09 19:35 created by orcl
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int i1 = 1; i1 <= i; i1++) {
                System.out.print(i + " * " + i1 + " = " + (i*i1) + "\t");

            }
            System.out.println();
        }
    }

}
