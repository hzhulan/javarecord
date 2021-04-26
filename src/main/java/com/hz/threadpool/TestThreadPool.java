package com.hz.threadpool;

import com.hz.threadpool.threadpool.BasicThreadPool;

import java.util.concurrent.TimeUnit;

/**
 * @file: TestThreadPool
 * @version: 1.0
 * @Description: 线程池测试
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
public class TestThreadPool {

    static class PrintWork implements Runnable {
        private int num;

        public PrintWork(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(String.format("%s运行中, 当前任务编号: %s", Thread.currentThread().getName(), this.num));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BasicThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(new PrintWork(i));

        }

        for (; ; ) {
            System.out.println("ActiveCount: " + threadPool.getActiveCount());
            System.out.println("QueueSize: " + threadPool.getQueueSize());
            System.out.println("CoreSize: " + threadPool.getCoreSize());
            System.out.println("MaxSize: " + threadPool.getMaxSize());
            System.out.println("=======================================");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
