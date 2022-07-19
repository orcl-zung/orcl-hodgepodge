package com.orcl.test.jpa;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-19 14:53
 * @history: 2022-07-19 14:53 created by orcl
 */
public class BaseDao<T> {

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
    }

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    private Class<T> beanClass;

    /**
     * 构造器
     * 初始化时完成对实际类型参数的获取，比如 BaseDao<User> 插入 User ，那么 beanClass 就是 User.class
     */
    public BaseDao() {
        beanClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void add(T bean) {
        // 得到 User  对象的所有字段
        Field[] declaredFields = beanClass.getDeclaredFields();

        StringBuilder sql = new StringBuilder()
                .append("insert into ")
                .append(beanClass.getSimpleName())
                .append(" values(");
        for (int i = 0; i < declaredFields.length; i++) {
            sql.append("?");
            if (i < declaredFields.length - 1) {
                sql.append(",");
            }
        }
        sql.append(")");

        List<Object> paramList = new ArrayList<>();

        try {
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object o = declaredField.get(bean);
                paramList.add(o);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int size = paramList.size();
        Object[] params = paramList.toArray(new Object[size]);

        int num = jdbcTemplate.update(sql.toString(), params);
        System.out.println(num);
    }

}
