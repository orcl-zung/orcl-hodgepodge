package com.orcl.springboot.tx;

import com.orcl.springboot.ApplicationTest;
import com.orcl.springboot.tx.service.TestTransactionEventService;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Test
    public void test_TransactionEvent() {
        service.testTransactionalEvent();
    }

    @Test
    public void test_code_transaction() {
        TransactionDefinition def = new DefaultTransactionDefinition();

        TransactionStatus status = platformTransactionManager.getTransaction(def);

        try {
            platformTransactionManager.commit(status);
        } catch (DataAccessException e) {
            platformTransactionManager.rollback(status);
            throw e;
        }

    }

}
