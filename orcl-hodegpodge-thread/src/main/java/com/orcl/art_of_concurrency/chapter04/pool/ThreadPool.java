package com.orcl.art_of_concurrency.chapter04.pool;

/**
 * @description:
 * @author: orcl
 * @since: 2022-12-13 21:08
 * @history: 2022-12-13 21:08 created by orcl
 */
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个 Job，这个 Job 需要实现 Runnable
     *
     * @param job 任务
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     *
     * @param num 数量
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     *
     * @param num 数量
     */
    void removeWorker(int num);

    /**
     * 得到正在等待执行的任务数量
     *
     * @return
     */
    int getJobSize();

}
