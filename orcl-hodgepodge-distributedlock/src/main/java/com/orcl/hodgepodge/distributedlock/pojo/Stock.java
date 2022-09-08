package com.orcl.hodgepodge.distributedlock.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: orcl
 * @since: 2022-09-01 15:39
 * @history: 2022-09-01 15:39 created by orcl
 */
@Data
@TableName("db_stock")
public class Stock {

    private Long id;

    private String productCode;

    private String warehouse;

    private Integer count;

}
