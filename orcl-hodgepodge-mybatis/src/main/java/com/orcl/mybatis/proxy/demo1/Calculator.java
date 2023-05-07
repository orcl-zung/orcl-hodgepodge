package com.orcl.mybatis.proxy.demo1;

/**
 * <p>
 * proxy 下面的 demo 代码主要就是说 mybatis 是基于接口去获取 xxMapper.xml 里的 SQL，主要实现参考 {@link org.apache.ibatis.session.defaults.DefaultSqlSession#getMapper(Class)} 方法
 * </p>
 *
 * @author lea
 * @history 2023-05-07 17:55 created by lea
 * @see org.apache.ibatis.session.defaults.DefaultSqlSession#getMapper(Class)
 * @since 2023-05-07 17:55
 */
public interface Calculator {

    int add(int a, int b);

    int sub(int a, int b);

}
