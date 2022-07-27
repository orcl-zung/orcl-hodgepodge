package com.orcl.reflection.anno;

import java.lang.annotation.*;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-20 23:25
 * @history: 2022-07-20 23:25 created by orcl
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    String value();

}
