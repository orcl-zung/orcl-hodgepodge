package com.orcl.guava_cache;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.orcl.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lea
 * @description
 * @history 2023-12-16 09:55 created by lea
 * @since 2023-12-16 09:55
 */
@Slf4j
public class GuavaCacheTest extends ApplicationTest {

    private static LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
            // 最大容量为 100 （基于容量进行回收）
            .maximumSize(100)
            // 配置写入后多久使缓存过期
            .expireAfterWrite(2, TimeUnit.MINUTES)
            // 配置写入后多久刷新缓存
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            // key 使用弱引用
            .weakKeys()
            // 当 Entry 被移除时的监听器
            .removalListener(notification -> log.info("notification: {}", JSON.toJSONString(notification)))
            // 创建一个 CacheLoader ，重写 load 方法，以实现“当 get 时，缓存不存在，则 load，放到缓存，并返回”的效果
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return "value_" + key;
                }

                @Override
                public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                    return super.reload(key, oldValue);
                }
            });

    @Test
    public void test_cacheLoader() throws ExecutionException {
        String value = loadingCache.get("1");
        log.info("value={}", value);
    }

    @Test
    public void test_callable() throws ExecutionException {
        String key = "1";
        String value = loadingCache.get(key, () -> "value_" + key);
        log.info("call value={}", value);
    }

}
