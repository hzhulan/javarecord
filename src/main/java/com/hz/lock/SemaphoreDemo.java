package com.hz.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: pp_lan on 2020/4/3.
 * @Description: Semaphore也是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位.");

                    try {
                        System.out.println(Thread.currentThread().getName() + "\t停车，3s后离开.");
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
