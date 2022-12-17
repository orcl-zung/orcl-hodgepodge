package com.orcl.art_of_concurrency.chapter05;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-17 01:14
 * @history: 2022-12-17 01:14 created by orcl
 */
public class Cache {

    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock rl = rwl.readLock();
    static ReentrantReadWriteLock.WriteLock wl = rwl.writeLock();

    /**
     * 获取 key 对应的 value
     *
     * @param key
     * @return
     */
    public static final Object get(String key) {
        rl.lock();
        try {
            return map.get(key);
        } finally {
            rl.unlock();
        }
    }

    /**
     * 设置 key 对应的 value，并返回旧的 value
     *
     * @param key
     * @param value
     * @return
     */
    public static final Object put(String key, Object value) {
        wl.lock();
        try {
            return map.put(key, value);
        } finally {
            wl.unlock();
        }
    }

    public static final void clear() {
        wl.lock();
        try {
            map.clear();
        } finally {
            wl.unlock();
        }
    }

}
