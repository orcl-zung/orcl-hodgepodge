package com.orcl.reflection;

import com.orcl.thread.test2.SimpleThreadPool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-18 16:46
 * @history: 2022-07-18 16:46 created by orcl
 */
public class ReflectionTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<SimpleThreadPool> clazz = SimpleThreadPool.class;
        SimpleThreadPool simpleThreadPool1 = clazz.getDeclaredConstructor(Integer.class, BlockingQueue.class)
                .newInstance(1, new ArrayBlockingQueue<Runnable>(3));
        System.out.println(simpleThreadPool1);


        Method execute = clazz.getMethod("execute", Runnable.class);
        execute.invoke(simpleThreadPool1, (Runnable) () -> System.out.println(Thread.currentThread().getName()));
    }

}
