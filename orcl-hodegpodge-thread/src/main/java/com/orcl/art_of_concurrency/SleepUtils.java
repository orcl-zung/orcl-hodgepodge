package com.orcl.art_of_concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-12 20:51
 * @history: 2022-12-12 20:51 created by orcl
 */
public class SleepUtils {

    /**
     * 睡眠-秒
     *
     * @param seconds
     */
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 睡眠-毫秒
     *
     * @param mills
     */
    public static final void millisSecond(long mills) {
        try {
            TimeUnit.MILLISECONDS.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
