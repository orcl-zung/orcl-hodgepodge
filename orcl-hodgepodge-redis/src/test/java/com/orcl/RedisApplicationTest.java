package com.orcl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-03 17:14
 * @history: 2022-08-03 17:14 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisApplicationTest {

    @Autowired
    RedissonClient redissonClient;

    @Test
    public void test_Redisson(){
        System.out.println(redissonClient);
    }

}
