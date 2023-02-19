package com.orcl.mode;

/**
 * @description: 单例模式
 * @author: orcl
 * @since: 2023-02-19 21:27
 * @history: 2023-02-19 21:27 created by orcl
 */
public class Singleton {

    /**
     * 饿汉式：
     * 类加载时就初始化，浪费内存，不能延迟加载；
     * 基于 ClassLoader 机制避免了多线程的同步问题，线程安全；
     * 没有加锁，调用效率高
     */
    static class Hunger {
        private static Hunger instance = new Hunger();

        private Hunger() {
        }

        public static Hunger getInstance() {
            return instance;
        }
    }

    /**
     * 懒汉式：
     * 线程不安全，延迟加载
     */
    static class Lazy {
        private static Lazy instance;

        private Lazy() {
        }

        public static Lazy getInstance() {
            if (instance == null) {
                instance = new Lazy();
            }
            return instance;
        }
    }

    /**
     * 线程安全的懒汉式：
     * 线程安全，延迟加载
     * getInstance 为同步方法，效率没这么高
     */
    static class LazyInSync {
        private static LazyInSync instance;

        private LazyInSync() {
        }

        public static synchronized LazyInSync getInstance() {
            if (instance == null) {
                instance = new LazyInSync();
            }
            return instance;
        }
    }

    /**
     * 双重检查锁的单例：
     * 双重判断，延迟加载；
     * 线程安全
     */
    static class DoubleCheckLock {
        private static DoubleCheckLock instance;

        private DoubleCheckLock() {
        }

        public static DoubleCheckLock getInstance() {
            if (instance == null) {
                synchronized (DoubleCheckLock.class) {
                    if (instance == null) {
                        instance = new DoubleCheckLock();
                    }
                }
            }
            return instance;
        }
    }

}
