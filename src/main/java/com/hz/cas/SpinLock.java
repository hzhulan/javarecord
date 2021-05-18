package com.hz.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @file: SpinLock
 * @version: 1.0
 * @Description: 自旋实现重入锁
 * @Author: pp_lan
 * @Date: 2021/5/18
 */
public class SpinLock {

    /**
    *  use thread itself as  synchronization state
    *  使用Owner Thread作为同步状态，比使用一个简单的boolean flag可以携带更多信息
    */
    private AtomicReference<Thread> owner = new AtomicReference<>();

    /**
     * reentrant count of a thread, no need to be volatile
     */
    private int count = 0;

    public void lock() {
        Thread t = Thread.currentThread();
        System.out.println("进入lock" + t.getName());
        // if re-enter, increment the count.
        if (t == owner.get()) {
            ++count;
            return;
        }

        //spin 当线程不为null的时候， 整体为true，自旋等待
        while (!owner.compareAndSet(null, t)) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void unlock() {
        Thread t = Thread.currentThread();
        //only the owner could do unlock;
        if (t == owner.get()) {
            if (count > 0) {
                // reentrant count not zero, just decrease the counter.
                --count;
            } else {
                // compareAndSet is not need here, already checked
                owner.set(null);
            }
        }
    }

    public static void main(String[] args) {
        SpinLock lock = new SpinLock();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        try {
            threadPool.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    lock.lock();
                    System.out.println("haha");
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threadPool.submit(() -> {
                try {
                    lock.lock();
                    System.out.println("haha2");
                    TimeUnit.SECONDS.sleep(10);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            try {
                threadPool.shutdown();
                if (!threadPool.awaitTermination(1, TimeUnit.MINUTES)) {
                     threadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                threadPool.shutdownNow();
            }
        }
    }
}
