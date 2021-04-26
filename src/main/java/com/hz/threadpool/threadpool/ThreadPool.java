package com.hz.threadpool.threadpool;

/**
 * @file: ThreadPool
 * @version: 1.1
 * @Description: 线程池接口
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
public interface ThreadPool {

    /**
     * 执行runnable任务
     * @param runnable
     */
    void execute(Runnable runnable);

    /**
     * 关闭
     */
    void shutdown();

    /**
     * 获取线程池初始化大小
     * @return
     */
    int getInitSize();

    /**
     * 获取线程池最大线程数
     * @return
     */
    int getMaxSize();

    /**
     * 获取核心线程数
     * @return
     */
    int getCoreSize();

    /**
     * 获取线程队列大小
     * @return
     */
    int getQueueSize();

    /**
     * 获取活跃线程数量
     * @return
     */
    int getActiveCount();

    /**
     * 查看是否关闭
     * @return
     */
    boolean isShutdown();
}
