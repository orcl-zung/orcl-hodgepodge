package com.orcl.springboot;

import ch.qos.logback.core.db.DBHelper;
import com.orcl.springboot.config.BeanConfig;
import com.orcl.springboot.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: Administrator
 * @since: 2022-08-06 11:48
 * @history: 2022-08-06 11:48 created by Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 测试 @Configuration 注解：
     * Full 模式 和 Lite 模式  @Configuration 注解的 proxyBeanMethods 属性为 true 为 Full 模式， 反之则为 Lite 模式
     * 配置类组件之间没有依赖关系 用 Lite 模式 加速容器启动过程，减少判断，Lite 模式：调用配置类里的 @Bean 注解方法会去 IoC 容器里找有没有，也就是说会返回这个 bean 的单例
     * 配置类组件之间有依赖关系，方法会被调用得到之前的单实例组件，用 Full 模式，使用 Full 模式能够保证组件单一，并且减少内存开销。
     */
    @Test
    public void test_configuration() {
        User u1 = applicationContext.getBean("user", User.class);
        User u2 = applicationContext.getBean("user", User.class);

        BeanConfig config = applicationContext.getBean("beanConfig", BeanConfig.class);
        User u3 = config.user();
        User u4 = config.user();

        System.out.printf("u1 = %s, u2 = %s, u3 = %s, u4 = %s%n", u1, u2, u3, u4);

    }

    /**
     * 测试 @Import 注解
     *  该注解可以注入 任意对象到 IoC 容器中，并且调用的是无参构造器
     */
    @Test
    public void test_import() {
        String[] beanNamesForType = applicationContext.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        DBHelper bean = applicationContext.getBean(DBHelper.class);
        System.out.println(bean);
    }

}
