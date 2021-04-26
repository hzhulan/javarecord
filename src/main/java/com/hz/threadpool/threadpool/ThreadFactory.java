package com.hz.threadpool.threadpool;

/**
 * @file: ThreadFactory
 * @version: 1.0
 * @Description: 线程工厂
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
public interface ThreadFactory {

    Thread createThread(Runnable runnable);

}
