package com.hz.programe;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: pp_lan on 2020/4/2.
 */
public class SingleTon {
    private static volatile SingleTon instance;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (instance == null) {
            synchronized (SingleTon.class) {
                if (instance ==null) {
                    instance = new SingleTon();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 200, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                new ThreadPoolExecutor.AbortPolicy());
        AtomicInteger num = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                SingleTon instance = SingleTon.getInstance();
                num.incrementAndGet();
                if (instance == null) {
                    System.out.println("instance 为空" + Thread.currentThread().getName());
                } else {
                    System.out.println(num);
                }
            });
        }
    }
}
