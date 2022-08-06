package com.orcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-16 10:56
 * @history: 2022-07-16 10:56 created by orcl
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HodgepodgeThreadApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(HodgepodgeThreadApplication.class, args);
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

}
