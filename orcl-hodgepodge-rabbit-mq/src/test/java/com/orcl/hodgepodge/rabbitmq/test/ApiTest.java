package com.orcl.hodgepodge.rabbitmq.test;

import com.orcl.hodgepodge.rabbit.mq.RabbitMqApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-05 11:25
 * @history: 2022-12-05 11:25 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RabbitMqApplication.class})
@Slf4j
public class ApiTest {

    @Autowired
    AmqpAdmin amqpAdmin;

    /*
        创建 exchange、queue、binding
     */

    @Test
    public void test_createExchange() {
        String name = "helle-java-exchange";
        DirectExchange directExchange = new DirectExchange(name, true, false);
        amqpAdmin.declareExchange(directExchange);
        log.info("exchange[{}] 创建成功！", name);
    }

    @Test
    public void test_createQueue() {
        String name = "helle-java-queue";
        Queue queue = new Queue(name, true, false, false);
        amqpAdmin.declareQueue(queue);
        log.info("queue[{}] 创建成功！", name);
    }

    @Test
    public void test_createBinding() {
        Binding binding = new Binding("helle-java-queue", Binding.DestinationType.QUEUE, "helle-java-exchange", "hello.java", null);
        amqpAdmin.declareBinding(binding);
        log.info("binding 创建成功！");
    }

    @Test
    public void test_charset() throws UnsupportedEncodingException {
        String s = "我爱中国！";
        byte[] bytes = s.getBytes(Charset.forName("GBK"));

        log.info("GBK 编码，GBK 解码：{}", new String(bytes, "GBK"));
        log.info("GBK 编码，GB18030 解码：{}", new String(bytes, "GB18030"));
        log.info("GBK 编码，UTF-8 解码：{}", new String(bytes, StandardCharsets.UTF_8));
    }



}
