package com.orcl.springboot.tx.listener;

import com.orcl.springboot.tx.event.TestTransactionEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author lea
 * @description
 * @history 2023-09-21 23:01 created by lea
 * @since 2023-09-21 23:01
 */
@Component
public class TestTransactionListener {

//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION, classes = TestTransactionEvent.class)
    public void sendMsg(TestTransactionEvent event) {
        String payload = event.getPayload();
        System.out.println(payload);
    }

}
