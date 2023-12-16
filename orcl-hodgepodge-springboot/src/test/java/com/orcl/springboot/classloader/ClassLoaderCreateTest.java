package com.orcl.springboot.classloader;

import com.orcl.springboot.ApplicationTest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lea
 * @description 创建对象的 5 种方式：1. 使用 new 关键字
 *                                2. 使用 Class 类 newInstance() 方法
 *                                3. 使用 Constructor 类 newInstance() 方法
 *                                4. 使用 clone() 方法
 *                                5. 使用反序列化
 * @history 2023-10-08 05:23 created by lea
 * @since 2023-10-08 05:23
 */
public class ClassLoaderCreateTest extends ApplicationTest {

    @Test
    public void test_createByClassLoader() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?> aClass = classLoader.loadClass("java.lang.String");
        Object dougLea = aClass.getConstructor(String.class).newInstance("Doug lea");
        System.out.println(dougLea);

        Class<String> stringClass = String.class;
    }

}
