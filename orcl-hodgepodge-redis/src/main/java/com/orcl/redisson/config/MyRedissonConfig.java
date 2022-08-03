package com.orcl.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
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

    public RedissonClient redisson() throws IOException {
        // 1. 创建配置
        Config config = new Config();

        // 集群模式
        config.useClusterServers().addNodeAddress("127.0.0.1:49153");

        // 根据 Config 创建出 RedissonClient 示例
        config.useSingleServer().setAddress("127.0.0.1:49153");
        return Redisson.create(config);
    }

}
