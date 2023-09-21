package com.orcl.springboot.tx.service.impl;

import com.orcl.springboot.tx.event.TestTransactionEvent;
import com.orcl.springboot.tx.service.TestTransactionEventService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lea
 * @description
 * @history 2023-09-21 23:20 created by lea
 * @since 2023-09-21 23:20
 */
@Service
public class TestTransactionEventServiceImpl implements TestTransactionEventService {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

//    @Transactional(rollbackFor = Exception.class)
    public void testTransactionalEvent() {
        System.out.println("方法开始执行。。。。");
        applicationEventPublisher.publishEvent(new TestTransactionEvent(this, "事务监听事件"));
        System.out.println("方法结束执行。。。。");
    }

}
