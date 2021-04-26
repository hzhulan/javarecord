package com.hz.threadpool.threadpool;

import com.hz.threadpool.queue.RunnableQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @file: InternalTask
 * @version: 1.0
 * @Description: 线程池主线程
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
public class InternalTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternalTask.class);

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }


    @Override
    public void run() {

        // 任务未被中断，不断执行queue中的任务
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }

    /**
     * 停止当前任务，调用线程池shutdown()方法执行
     */
    public void stop() {
        this.running = false;
    }
}
