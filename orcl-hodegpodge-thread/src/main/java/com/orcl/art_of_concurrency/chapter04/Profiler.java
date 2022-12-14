package com.orcl.art_of_concurrency.chapter04;

import com.orcl.art_of_concurrency.SleepUtils;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-13 16:55
 * @history: 2022-12-13 16:55 created by orcl
 */
public class Profiler {

    // 第一次 get() 方法调用时会进行初始化（如果 set 方法没有调用）。每个线程都会调用一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = ThreadLocal.withInitial(System::currentTimeMillis);

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) {
        Profiler.begin();
        SleepUtils.second(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
