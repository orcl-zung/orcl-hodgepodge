package com.orcl.demo.redis.cache.anno;

import java.lang.annotation.*;

/**
 * @description:
 * @author: orcl
 * @since: 2022/4/25-13:46
 * @history: 2022/4/25 created by orcl
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    String value() default "";

    String key() default "";

}
