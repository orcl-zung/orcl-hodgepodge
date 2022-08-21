package com.orcl.test.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-12 14:40
 * @history: 2022-08-12 14:40 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamApiTest {

    @Test
    public void test_predicate() {

        int i = 0;

        for (foo("A"); i < 2 && foo("B"); foo("D")) {
            i++;
            System.out.print("C");
        }

    }

    public boolean foo(String str) {
        System.out.print(str);
        return true;
    }

}
