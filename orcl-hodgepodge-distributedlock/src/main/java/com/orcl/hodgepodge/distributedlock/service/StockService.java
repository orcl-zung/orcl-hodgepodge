package com.orcl.hodgepodge.distributedlock.service;

import com.orcl.hodgepodge.distributedlock.pojo.Stock;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: orcl
 * @since: 2022-09-01 15:42
 * @history: 2022-09-01 15:42 created by orcl
 */
@Service
public class StockService {

    private Stock stock = new Stock();

    private ReentrantLock lock = new ReentrantLock();

    public void deduct() {

        lock.lock();
        try {
            stock.setStock(stock.getStock() - 1);
            System.out.println("库存余量：" + stock.getStock());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
