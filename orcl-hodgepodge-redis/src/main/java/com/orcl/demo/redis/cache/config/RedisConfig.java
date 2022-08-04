package com.orcl.demo.redis.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.orcl.demo.redis.cache.service.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

/**
 * @description:
 * @author: orcl
 * @since: 2022/4/25-13:51
 * @history: 2022/4/25 created by orcl
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 缓存基本常量
     */
    String BASE_CACHE_KEY = ":cache:";

    /**
     * 缓存基本常量
     */
    String REDIS_LOCK_CACHE_KEY = "robot-redis-lock:";

    @Value("${spring.application.name")
    private String applicationName;

    @Value("${spring.lock.expireAfter}")
    private Long lockExpireAfter;

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        template.setValueSerializer(serializer);

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisService redisService(RedisTemplate<Object, Object> redisTemplate) {
        return new RedisService(redisTemplate);
    }

    @Bean("redisCacheManager")
    public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofDays(1))
                .disableCachingNullValues()
                .prefixCacheNameWith(applicationName + BASE_CACHE_KEY)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);

        ImmutableSet.Builder<String> cacheNames = ImmutableSet.builder();

        ImmutableMap.Builder<String, RedisCacheConfiguration> cacheConfig = ImmutableMap.builder();

        Map<String, Duration> initCaches = Properties.getInitCaches();

        Optional.of(initCaches).ifPresent(map -> {
            map.forEach((k, v) -> {
                cacheNames.add(k);
                cacheConfig.put(k, cacheConfiguration.entryTtl(v));
            });
        });

        return RedisCacheManager.builder(redisCacheWriter)
                .cacheDefaults(cacheConfiguration)
                .initialCacheNames(cacheNames.build())
                .withInitialCacheConfigurations(cacheConfig.build())
                .build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.lock", value = "enabled", havingValue = "true")
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, REDIS_LOCK_CACHE_KEY + applicationName + ":", lockExpireAfter);
    }


}
