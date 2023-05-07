package com.orcl.mybatis;

import com.orcl.mybatis.mapper.UserMapper;
import com.orcl.mybatis.pojo.User;
import com.orcl.mybatis.pojo.query.UserQueryBean;
import com.orcl.mybatis.proxy.demo1.CalculatorImpl;
import com.orcl.mybatis.proxy.demo1.CalculatorProxy;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author lea
 * @description
 * @history 2023-05-03 15:10 created by lea
 * @since 2023-05-03 15:10
 */
public class ApiTest extends ApplicationTest {

    private static SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        String resource = "mybatis/mybatis-config.xml";
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resource);
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testInitMybatis() throws IOException {

        SqlSession sqlSession = SQL_SESSION_FACTORY.openSession();
        UserQueryBean queryBean = new UserQueryBean();
        queryBean.setUserName("pdai");

        List<User> list = sqlSession.selectList("com.orcl.mybatis.mapper.UserMapper.findList", queryBean);
        System.out.println(list);
    }

    @Test
    public void testTypeAliasRegistry() {
        Configuration configuration = SQL_SESSION_FACTORY.getConfiguration();
        TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
        Map<String, Class<?>> typeAliases = typeAliasRegistry.getTypeAliases();
        typeAliases.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    @Test
    public void testMapperRegistry() {
        Configuration configuration = SQL_SESSION_FACTORY.getConfiguration();
        MapperRegistry mapperRegistry = configuration.getMapperRegistry();
        Collection<Class<?>> mappers = mapperRegistry.getMappers();
        mappers.forEach(System.out::println);
    }

    @Test
    public void testGetMapper() {
        SqlSession sqlSession = SQL_SESSION_FACTORY.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findById(1L);
        System.out.println(user);
    }

    @Test
    public void testStaticProxy() {
        CalculatorImpl ci = new CalculatorImpl();
        CalculatorProxy calculatorProxy = new CalculatorProxy(ci);
        System.out.println(calculatorProxy.add(1, 2));
        System.out.println(calculatorProxy.sub(4, 2));

    }

}
