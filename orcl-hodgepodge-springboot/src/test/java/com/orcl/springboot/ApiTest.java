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
     * 该注解可以注入 任意对象到 IoC 容器中，并且调用的是无参构造器
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

    /**
     * 测试 @Conditional 以及 子注解
     * 该 @Conditional 注解是一个条件装配注解，主要用于限制 @Bean 注解在什么时候才生效。以指定的条件形式控制 bean 的创建
     * 该 @Conditional 可以自定义条件进行装配或者不装配…
     * 该 @Conditional 本身还是一个父注解，派生出大量的子注解；可以按需加载！
     * <p>
     * 因此在学习 SpringBoot 的时候是非常有必要学习这个注解的使用的，SpringBoot就是按需加载。
     * <p>
     * Conditional 注解和所有子注解首先必须依托 @Configuration 配置类注解
     * <p>
     * 都可以加载类或者方法上；加载类上的含义所有的方法都按照这个条件装配、加载方法上只有该方法进行条件装配。
     * <p>
     * 注：Conditional 注解是 Spring4.0 就有的，旗下的子注解是 SpringBoot1.0 有的。
     * Conditional 注解应该只能用在同一个类的 Bean 加载中，同时搭配 Configuration 注解使用，此外限定某个 bean 加载的时候和代码编写顺序有关
     */
    @Test
    public void test_conditional() {
        boolean user = applicationContext.containsBean("user");
        boolean pet = applicationContext.containsBean("pet");
//        String[] users = applicationContext.getBeanNamesForType(User.class);
//        for (String s : users) {
//            System.out.println(s);
//        }
        System.out.println("容器中的 user 组件：" + user);
        System.out.println("容器中的 pet 组件：" + pet);
    }

}
