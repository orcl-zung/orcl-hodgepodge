package com.orcl.reflection.anno;

import java.lang.annotation.*;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-19 09:44
 * @history: 2022-07-19 09:44 created by orcl
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoTest {

    String value() default "default value";

}
