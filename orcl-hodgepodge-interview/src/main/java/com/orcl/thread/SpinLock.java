package com.orcl.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 自旋锁实现，参考：https://mp.weixin.qq.com/s/jEx-4XhNGOFdCo4Nou5tqg
 * @author: orcl
 * @since: 2023-03-07 14:05
 * @history: 2023-03-07 14:05 created by orcl
 */
public class SpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    public void lock() {
        Thread curThread = Thread.currentThread();

        // 如果锁未被占用，则设置当前线程为锁的拥有者
        while (!owner.compareAndSet(null, curThread)) {

        }
    }

    public void unlock() {
        Thread curThread = Thread.currentThread();

        // 只有锁的拥有者才能释放锁
        owner.compareAndSet(curThread, null);
    }

}
