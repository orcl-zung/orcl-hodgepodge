package com.orcl.redis.cache.config;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static com.orcl.redis.cache.constant.CacheConstant.*;

/**
 * @description:
 * @author: orcl
 * @since: 2022/4/25-17:28
 * @history: 2022/4/25 created by orcl
 */
@Configuration
public class Properties {


    private static final Map<String, Duration> INIT_CACHES = Maps.newHashMap();

    public static Map<String, Duration> getInitCaches() {
        INIT_CACHES.put(HUAWEI_TOKEN_KEY, Duration.of(1L, ChronoUnit.HOURS));
        INIT_CACHES.put(OPPO_TOKEN_KEY, Duration.of(1L, ChronoUnit.HOURS));
        INIT_CACHES.put(VIVO_TOKEN_KEY, Duration.of(1L, ChronoUnit.HOURS));
        return INIT_CACHES;
    }
}
