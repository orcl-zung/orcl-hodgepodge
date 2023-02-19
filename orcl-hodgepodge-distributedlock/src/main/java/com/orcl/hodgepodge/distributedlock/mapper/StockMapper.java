package com.orcl.hodgepodge.distributedlock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orcl.hodgepodge.distributedlock.pojo.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @description:
 * @author: orcl
 * @since: 2022-09-02 00:04
 * @history: 2022-09-02 00:04 created by orcl
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

    @Update("update db_stock set count = count - #{count} where product_code = #{productCode} and count >= #{count}")
    int updateStock(@Param("productCode") String productCode, @Param("count") Integer count);

}
