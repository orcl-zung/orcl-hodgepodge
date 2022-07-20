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
        dataSource.setUrl("jdbc:mysql://localhost:3306/orcl_hodgepodge");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
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

    public int add(T bean) {
        // 得到 User 对象的所有字段
        Field[] declaredFields = beanClass.getDeclaredFields();

        // 拼接 sql 语句，表名直接用 POJO 的类名，所以创建表时，请注意写成 User ，而不是 t_user
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

        // 获得 bean 字段的值（要插入的记录）
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

        // 传入sql语句模板和模板所需的参数，插入User
        int num = jdbcTemplate.update(sql.toString(), params);
        System.out.println(num);
        return num;
    }

}
