package com.orcl.reflection.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 自定义的Junit测试
 * @author: orcl
 * @since: 2022-07-19 13:59
 * @history: 2022-07-19 13:59 created by orcl
 */
public class MyJunitFramework {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<EmpDao> clazz = EmpDao.class;
        EmpDao obj = clazz.getDeclaredConstructor().newInstance();

        Method[] methods = clazz.getMethods();

        List<Method> myBeforeList = new ArrayList<>();
        List<Method> myTestList = new ArrayList<>();
        List<Method> myAfterList = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBefore.class))
                myBeforeList.add(method);
            else if (method.isAnnotationPresent(MyTest.class))
                myTestList.add(method);
            else if (method.isAnnotationPresent(MyAfter.class))
                myAfterList.add(method);
        }

        for (Method method : myTestList) {

            for (Method method1 : myBeforeList) {
                method1.invoke(obj);
            }

            method.invoke(obj);

            for (Method method1 : myAfterList) {
                method1.invoke(obj);
            }

        }

    }

}
