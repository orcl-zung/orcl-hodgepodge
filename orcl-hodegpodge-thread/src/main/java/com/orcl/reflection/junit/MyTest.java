package com.orcl.reflection.junit;

import java.lang.annotation.*;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-19 11:06
 * @history: 2022-07-19 11:06 created by orcl
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest {
}
