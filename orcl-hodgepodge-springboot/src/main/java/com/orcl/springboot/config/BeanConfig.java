package com.orcl.springboot.config;

import ch.qos.logback.core.db.DBHelper;
import com.orcl.springboot.pojo.Pet;
import com.orcl.springboot.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-06 11:29
 * @history: 2022-08-06 11:29 created by Administrator
 */
@Configuration
@Import({User.class, DBHelper.class})
public class BeanConfig {

    @Bean
    public User user() {
        return new User("orcl", 18);
    }

    @Bean
    public Pet pet() {
        return new Pet("Chopper");
    }

}
