package com.hz.threadpool.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @file: DefaultThreadFactory
 * @version: 1.0
 * @Description: 默认线程工厂
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
public class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger GROUP_COUNT = new AtomicInteger(1);

    private static final AtomicInteger THREAD_COUNT = new AtomicInteger(1);

    private static final ThreadGroup group = new ThreadGroup("ThreadPoolGroup-" + GROUP_COUNT.getAndIncrement());

    @Override
    public Thread createThread(Runnable runnable) {
        return new Thread(group, runnable, String.format("【线程-%s】", GROUP_COUNT.getAndIncrement(), THREAD_COUNT.getAndIncrement()));
    }
}
