package com.hz.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @authod: pp_lan on 2020/4/8.
 */
public class LockDemo {

    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increase() {
        lock.lock();

        try {
            if (count != 0) {
                condition.await();
            }
            count++;
            System.out.println(count);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrease() {
        lock.lock();

        try {
            if (count == 0) {
                condition.await();
            }
            count--;
            System.out.println(count);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo demo = new LockDemo();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                demo.decrease();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                demo.increase();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                demo.decrease();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                demo.increase();
            }
        }).start();
    }
}
