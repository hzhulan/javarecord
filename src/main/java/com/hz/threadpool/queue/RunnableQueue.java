package com.hz.threadpool.queue;

/**
 * @file: RunnableQueue
 * @version: 1.1
 * @Description: 任务队列
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
public interface RunnableQueue {

    /**
     * 添加任务
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 获取任务
     * @return
     */
    Runnable take();

    /**
     * 队列任务数量
     * @return
     */
    int size();
}
