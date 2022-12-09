package com.orcl.hodgepodge.rabbit.mq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-05 11:17
 * @history: 2022-12-05 11:17 created by orcl
 */
@SpringBootApplication
@EnableRabbit
public class RabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
    }

}
