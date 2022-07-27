package com.orcl.redis.cache.config;

import com.orcl.redis.cache.service.DistService;
import com.orcl.redis.cache.service.RedisService;
import net.sf.ehcache.CacheManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: orcl
 * @since: 2022/4/25-17:33
 * @history: 2022/4/25 created by orcl
 */
@Configuration
@AutoConfigureAfter(RedisConfig.class)
public class EhcacheConfig {

    @Bean("ehCacheManager")
    public net.sf.ehcache.CacheManager ehCacheCacheManager() {
        return net.sf.ehcache.CacheManager.create();
    }

    @Bean
    public DistService distService(CacheManager cacheManager, RedisService redisService) {
        DistService distService = new DistService(cacheManager);
        distService.setRedisService(redisService);
        return distService;
    }

}
