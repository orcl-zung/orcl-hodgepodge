package com.orcl.exception;

import com.orcl.ApplicationTest;
import org.junit.Test;

/**
 * @author lea
 * @description
 * @history 2023-05-25 23:19 created by lea
 * @since 2023-05-25 23:19
 */
public class ExceptionTest extends ApplicationTest {

    @Test
    public void testException() {
        String s = "123";
        if ("123".equals(s)) {
            logger.error("customer exception");
            logger.info("customer exception");
            logger.warn("customer exception");
            logger.trace("customer exception");
            throw new CustomerException("customer exception");
        }
    }

    @Test
    public void test_Byte_ConstantPool() {

        byte b = (byte) 217;

        Byte aByte = Byte.valueOf(b);

        System.out.println(aByte);

    }

    @Test
    public void test_Integer_ConstantPool() {
        Integer integer = Integer.valueOf(1444);
        System.out.println(integer);
    }

}
