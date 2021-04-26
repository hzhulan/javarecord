package com.hz.threadpool.threadpool;

/**
 * @file: ThreadPoolException
 * @version: 1.0
 * @Description: 任务拒绝异常
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
public class ThreadPoolException extends RuntimeException {

    public ThreadPoolException() {
        super();
    }

    public ThreadPoolException(String msg) {
        super(msg);
    }

    public ThreadPoolException(Throwable cause) {
        super(cause);
    }

    public ThreadPoolException(String msg, Throwable e) {
        super(msg, e);
    }
}
