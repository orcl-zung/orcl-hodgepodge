package com.orcl.thread;

import java.util.concurrent.*;

/**
 * @description: 几种线程池的类型
 * @author: orcl
 * @since: 2023-03-07 20:07
 * @history: 2023-03-07 20:07 created by orcl
 */
public class ThreadPoolType {

    /*
     * 注意：
     *      FixedThreadPool 和 SingleThreadExecutor 使用的是无界的 LinkedBlockingQueue ，任务队列最大长度为 Integer.MAX_VALUE
     *      可能堆积大量的请求，从而导致 OOM。
     *
     *      CachedThreadPool 使用的是同步队列 SynchronousQueue ，最大线程数为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致 OOM
     *
     *      ScheduleThreadPool 和 SingleThreadScheduleExecutor 使用的是无界的延迟阻塞队列 DelayedWorkQueue ，任务队列的最大长度
     *      为 Integer.MAX_VALUE ，可能堆积大量的请求，从而导致 OOM 。
     */


    /**
     * 该方法返回一个固定线程数量的线程池。该线程池中的线程数量始终不变。当有一个新的任务提交时，线程池中若有空闲线程，则立即执行。
     * 若没有，则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务。
     * <p>
     * 无界队列 LinkedBlockingQueue
     *
     * @param nThreads 核心线程数和最大线程数
     * @return 线程池对象
     */
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    /**
     * 该方法返回一个只有一个线程的线程池。若多余一个任务被提交到该线程池，任务会被保存在一个任务队列中，
     * 待线程空闲，按先入先出（FIFO）的顺序执行队列中的任务。
     * <p>
     * 无界队列 LinkedBlockingQueue
     *
     * @return 线程池对象
     */
    public static ExecutorService newSingleThreadExecutor() {
//        return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1,
//                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
        return null;
    }

    /**
     * 该方法返回一个可根据实际情况调整线程数量的线程池。线程池的线程数量不确定，但若有空闲线程可以复用，则会优先使用可复用的线程。
     * 若所有线程均在工作，又有新的任务提交，则会创建新的线程处理任务。所有线程在当前任务执行完毕后，将返回线程池进行复用。
     * <p>
     * 同步队列 SynchronousQueue ，没有容量，最大线程数是 Integer.MAX_VALUE
     *
     * @return 线程池对象
     */
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }

    /**
     * 该方法返回一个用来在给定的延迟后运行任务或者定期执行任务的线程池
     * <p>
     * 延迟阻塞队列 DelayedWorkQueue
     *
     * @param corePoolSize 核心线程数
     * @return 线程池对象
     */
    public static ScheduledExecutorService newScheduleThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

//    public ScheduledThreadPoolExecutor(int corePoolSize) {
//        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
//                new DelayedWorkQueue());
//    }

}
