package com.hz.threadpool.threadpool;

import com.hz.threadpool.queue.LinkedRunnableQueue;
import com.hz.threadpool.queue.RunnableQueue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @file: BasicThreadPool
 * @version: 1.0
 * @Description: 基础线程池
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
public class BasicThreadPool extends Thread implements ThreadPool {

    private final int initSize;

    private final int maxSize;

    private final int coreSize;

    private int activeCount;

    private final ThreadFactory threadFactory;

    private final RunnableQueue runnableQueue;

    private volatile boolean isShutdown = false;

    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10,
                TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize,
                           ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, long keepAliveTime,
                           TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;

        this.init();
    }

    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown) {
            throw new ThreadPoolException("线程池已关闭");
        }
        this.runnableQueue.offer(runnable);
    }

    private void newThread() {

        // 线程池创建的 执行任务的线程
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);

        // 创建Runnable和其Thread对象的关系
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;

        thread.start();
    }


    private void removeThread() {
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void run() {
        while (!isShutdown && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }

            synchronized (this) {

                // 线程池关闭
                if (isShutdown) {
                    break;
                }

                // 任务队列中有任务未处理 --> 初始化的线程不够用，扩展
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                }

                // 核心线程数不够用
                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }

                // 无任务，活动线程缩减到核心线程数
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = activeCount; i > coreSize; i--) {
                        removeThread();
                    }
                }

            }
        }


    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }

            threadQueue.forEach(threadTask -> {

                // 线程停止
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
        }
    }

    @Override
    public int getInitSize() {
        if(isShutdown) {
            throw new ThreadPoolException("线程池已销毁");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if(isShutdown) {
            throw new ThreadPoolException("线程池已销毁");
        }
        return maxSize;
    }

    @Override
    public int getCoreSize() {
        if(isShutdown) {
            throw new ThreadPoolException("线程池已销毁");
        }
        return coreSize;
    }

    @Override
    public int getQueueSize() {
        if(isShutdown) {
            throw new ThreadPoolException("线程池已销毁");
        }
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }


    private static class ThreadTask {

        private Thread thread;

        private InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }

        //<editor-fold desc="setter and getter">
        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public InternalTask getInternalTask() {
            return internalTask;
        }

        public void setInternalTask(InternalTask internalTask) {
            this.internalTask = internalTask;
        }
        //</editor-fold>
    }
}
