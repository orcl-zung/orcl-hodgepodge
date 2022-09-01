package com.orcl.hodgepodge.distributedlock.controller;

import com.orcl.hodgepodge.distributedlock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: orcl
 * @since: 2022-09-01 15:40
 * @history: 2022-09-01 15:40 created by orcl
 */
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock/deduct")
    public String deduct() {
        this.stockService.deduct();
        return "hello stock deduct!!";
    }

}
