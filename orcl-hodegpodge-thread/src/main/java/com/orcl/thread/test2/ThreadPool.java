package com.orcl.thread.test2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-11 11:04
 * @history: 2022-07-11 11:04 created by orcl
 */
public class ThreadPool {

    /**
     * 工作线程
     */
    private final ReentrantLock mainLock = new ReentrantLock();

    private final List<Worker> workers = new ArrayList<>();

    private BlockingQueue<Runnable> workQueue;

    private final int corePoolSize;

    private final int maximumPoolSize;

    private final long keepAliveTime;

    public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = timeUnit.toNanos(keepAliveTime);
        this.workQueue = workQueue;
    }

    public void execute(Runnable task) {
        Assert.notNull(task, "task is null");
        // 创建核心线程处理任务
        if (workers.size() < corePoolSize) {
            this.addWorker(task, true);
            return;
        }

        // 尝试加入任务队列
        boolean enqueued = workQueue.offer(task);
        if (enqueued) return;

        // 创建非核心线程处理任务
        if (!this.addWorker(task, false)) {
            throw new RuntimeException("拒绝策略");
        }
    }

    private boolean addWorker(Runnable task, boolean core) {
        int wc = workers.size();
        if (wc >= (core ? corePoolSize : maximumPoolSize)) {
            return false;
        }
        boolean workerStarted = false;
        try {
            Worker worker = new Worker(task);
            final Thread thread = worker.getThread();
            if (thread != null) {
                mainLock.lock();
                workers.add(worker);
                thread.start();
                workerStarted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mainLock.unlock();
        }
        return workerStarted;
    }

    private void runWorker(Worker worker) {
        Runnable task = worker.getTask();

        try {
            while (task != null || (task = getTask()) != null) {
                task.run();
                task = null;
            }
        } finally {
            /*
             * 从循环退出来，意味着当前线程是非核心线程，而且需要被销毁
             * Java 的线程，既可以指代 Thread 对象，也可以指代 JVM 线程，一个 Thread 对象绑定一个 JVM 线程
             * 因此，线程的销毁分为两个维度：1. 把 Thread 对象从 workers 移除，
             *                          2. JVM 线程执行完当前任务，会自然销毁
             */
            workers.remove(worker);
        }
    }

    private Runnable getTask() {
        boolean timeOut = false;

        for (; ; ) {
            boolean timed = workers.size() > corePoolSize;

            if (timed && timeOut) {
                return null;
            }
            try {
                /*
                 * 是否需要检测超时
                 * 1、需要：poll 阻塞获取，等待 keepAliveTime，等待结束就返回，不管有没有获取到任务
                 * 2、不需要：take 持续阻塞，知道获取结果
                 */
                Runnable r = timed ? workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                        workQueue.take();
                if (r != null) {
                    return r;
                }
                timeOut = true;
            } catch (InterruptedException retry) {
                timeOut = false;
            }
        }
    }

    @Getter
    @Setter
    private class Worker implements Runnable {

        private Thread thread;
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
            thread = new Thread(this);
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }

}
