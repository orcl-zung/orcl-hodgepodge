package com.orcl.test.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-05 21:57
 * @history: 2022-08-05 21:57 created by Administrator
 */
public class Demo1Test {


    @Test
    public void test_lambda_method_pass() {
        String s1 = "abc";
        String s2 = "abcd";

        int i = compareString(s1, s2, Comparator.comparingInt(String::length));

        System.out.println(i);
    }

    private int compareString(String s1, String s2, Comparator<String> comparator) {
        return comparator.compare(s1, s2);
    }

}
