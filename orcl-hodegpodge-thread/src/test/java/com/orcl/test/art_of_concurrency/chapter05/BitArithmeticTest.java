package com.orcl.test.art_of_concurrency.chapter05;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: 位运算测试
 * @author: orcl
 * @since: 2022-12-17 01:33
 * @history: 2022-12-17 01:33 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BitArithmeticTest {

    private final int A = 60;
    private final int B = 13;

    @Test
    public void test_bit_arithmetic() {
        bitArithmetic(A, B);
    }

    private void bitArithmetic(int x, int y) {
        System.out.println("与运算（&）：" + (A & B));
        System.out.println("或运算（|）：" + (A | B));
        System.out.println("异或运算（^）：" + (A ^ B));
    }

    /**
     * 与运算：遇 0 则 0
     */
    @Test
    public void test_and() {
        System.out.println(A & B);
    }

    /**
     * 或运算：遇 1 则 1
     */
    @Test
    public void test_or() {
        System.out.println(A | B);
    }

    /**
     * 异或运算：同 0 异 1
     */
    @Test
    public void test_xor() {
        System.out.println(A ^ B);
    }

}
