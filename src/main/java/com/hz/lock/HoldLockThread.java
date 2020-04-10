package com.hz.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁demo
 * @authod: pp_lan on 2020/4/9.
 */
public class HoldLockThread implements Runnable {


    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t持有：" + lockA + "\t尝试获得" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t持有" + lockB + "\t尝试获得" + lockA);
            }
        }
    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "thread-a").start();
        new Thread(new HoldLockThread(lockB, lockA), "thread-b").start();

    }
}
