package com.orcl.demo.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-03 17:15
 * @history: 2022-08-03 17:15 created by orcl
 */
@Configuration
public class MyRedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() throws IOException {
        // 1. 创建配置
        Config config = new Config();

        // 集群模式
//        config.useClusterServers().addNodeAddress("127.0.0.1:49153");

        // 根据 Config 创建出 RedissonClient 示例
        config.useSingleServer().setAddress(host + port).setPassword(password);
        return Redisson.create(config);
    }

}
