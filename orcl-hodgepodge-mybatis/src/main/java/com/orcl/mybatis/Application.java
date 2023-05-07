package com.orcl.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lea
 * @description
 * @history 2023-05-02 19:06 created by lea
 * @since 2023-05-02 19:06
 */
@MapperScan({"com.orcl.mybatis.mapper"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
