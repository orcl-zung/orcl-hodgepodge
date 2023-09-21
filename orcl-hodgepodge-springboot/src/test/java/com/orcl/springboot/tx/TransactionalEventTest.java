package com.orcl.springboot.tx;

import com.orcl.springboot.ApplicationTest;
import com.orcl.springboot.tx.service.TestTransactionEventService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author lea
 * @description
 * @history 2023-09-21 23:23 created by lea
 * @since 2023-09-21 23:23
 */
public class TransactionalEventTest extends ApplicationTest {

    @Resource
    private TestTransactionEventService service;

    @Test
    public void test_TransactionEvent() {
        service.testTransactionalEvent();
    }

}
