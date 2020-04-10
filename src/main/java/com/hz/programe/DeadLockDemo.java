package com.hz.programe;

import java.util.concurrent.TimeUnit;

/**
 * @authod: pp_lan on 2020/3/26.
 */
public class DeadLockDemo {

    private static final String lock1 = "lock1";
    private static final String lock2 = "lock2";

    public static void main(String[] args) {
        new Thread(new DeadRunnable1()).start();
        new Thread(new DeadRunnable2()).start();
    }

    static class DeadRunnable1 implements Runnable {

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " get lock1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "get lock2");
                }
            }
        }
    }

    static class DeadRunnable2 implements Runnable {

        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "get lock2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "get lock1");
                }
            }
        }
    }
}
