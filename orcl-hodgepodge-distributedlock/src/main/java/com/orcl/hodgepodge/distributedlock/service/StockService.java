package com.orcl.hodgepodge.distributedlock.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orcl.hodgepodge.distributedlock.mapper.StockMapper;
import com.orcl.hodgepodge.distributedlock.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: orcl
 * @since: 2022-09-01 15:42
 * @history: 2022-09-01 15:42 created by orcl
 */
@Service
//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockService {

//    private Stock stock = new Stock();

    @Autowired
    private StockMapper stockMapper;

    private ReentrantLock lock = new ReentrantLock();

    //    @Transactional(rollbackFor = Exception.class)
    public void deduct() {

        lock.lock();
        try {
            Stock stock = stockMapper.selectOne(new QueryWrapper<Stock>().eq("product_code", "1001"));
            Integer count;
            if (stock != null && (count = stock.getCount()) > 0) {
                stock.setCount(--count);
                stockMapper.updateById(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
