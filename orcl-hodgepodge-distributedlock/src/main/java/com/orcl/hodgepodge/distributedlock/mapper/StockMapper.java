package com.orcl.hodgepodge.distributedlock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orcl.hodgepodge.distributedlock.pojo.Stock;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: orcl
 * @since: 2022-09-02 00:04
 * @history: 2022-09-02 00:04 created by orcl
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {
}
