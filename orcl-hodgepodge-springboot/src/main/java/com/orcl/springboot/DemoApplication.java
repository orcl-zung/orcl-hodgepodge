package com.orcl.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-06 11:16
 * @history: 2022-08-06 11:16 created by Administrator
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("run的具体类型是：" + run);
    }

}