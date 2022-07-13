package com.orcl.thread.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-11 09:57
 * @history: 2022-07-11 09:57 created by orcl
 */
public class SimpleThreadPool {

    /**
     * 任务队列
     */
    BlockingQueue<Runnable> workQueue;

    /**
     * 工作线程
     */
    List<Worker> workers = new ArrayList<>();

    public SimpleThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        // 创建线程，执行任务，并加入线程池
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker();
            worker.start();
            workers.add(worker);
        }

    }

    /**
     * 提交任务
     *
     * @param command 任务
     */
    public void execute(Runnable command) {
        try {
            workQueue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Worker extends Thread {
        @Override
        public void run() {
            // 循环后去任务，如果任务为空则阻塞等待
            while (true) {
                try {
                    Runnable task = workQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
