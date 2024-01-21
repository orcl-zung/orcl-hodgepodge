package com.orcl.demo.redisson.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-04 21:07
 * @history: 2022-08-04 21:07 created by orcl
 */
@RestController
public class DemoController {

//    @Resource
//    private Redisson redisson;

    @ResponseBody
    @GetMapping("/test-lock")
    public String testLock() {
//        // 1. 获取锁，只要锁的名字一样，获取到的锁就是同一把锁
//        RLock lock = redisson.getLock("orcl-lock");
//
//        // 2. 加锁
//        lock.lock();
//        try {
//            System.out.println("加锁成功，执行后续代码。线程ID：" + Thread.currentThread().getId());
//            TimeUnit.SECONDS.sleep(10);
//        } catch (Exception e) {
//            // TODO
//        } finally {
//            lock.unlock();
//
//            // 3. 解锁
//            System.out.println("Finally，释放锁成功。线程ID：" + Thread.currentThread().getId());
//        }
//        return "test lock ok";
        return null;
    }

}
