package com.orcl.springboot;

import com.orcl.springboot.config.BeanConfig;
import com.orcl.springboot.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-06 11:48
 * @history: 2022-08-06 11:48 created by Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test_configuration() {
        User u1 = applicationContext.getBean("user", User.class);
        User u2 = applicationContext.getBean("user", User.class);

        BeanConfig config = applicationContext.getBean("beanConfig", BeanConfig.class);
        User u3 = config.user();
        User u4 = config.user();

        System.out.printf("u1 = %s, u2 = %s, u3 = %s, u4 = %s%n", u1, u2, u3, u4);

    }

}
