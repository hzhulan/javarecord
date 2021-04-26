package com.hz.threadpool.queue;

import com.hz.threadpool.threadpool.DenyPolicy;
import com.hz.threadpool.threadpool.ThreadPool;
import com.hz.threadpool.threadpool.ThreadPoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

/**
 * @file: LinkedRunnableQueue
 * @version: 1.0
 * @Description: 链表
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
public class LinkedRunnableQueue implements RunnableQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkedRunnableQueue.class);

    /**
     * 队列最大容量
     */
    private final int limit;

    /**
     * 拒绝策略
     */
    private final DenyPolicy denyPolicy;

    /**
     * 存放任务的队列
     */
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    /**
     * 线程池
     */
    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if (runnableList.size() >= limit) {
                denyPolicy.reject(runnable, threadPool);
            } else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() {
        synchronized (runnableList) {
            while (runnableList.isEmpty()) {
                try {
                    runnableList.wait();
                } catch (InterruptedException e) {
                    throw new ThreadPoolException(e);
                }
            }

            // 从任务中取出第一个
            return runnableList.removeFirst();
        }

    }

    @Override
    public int size() {
        synchronized (runnableList) {
            return runnableList.size();
        }
    }
}
