package com.hz.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @file: SpinLock
 * @version: 1.0
 * @Description: 重入锁
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
        // if re-enter, increment the count.
        if (t == owner.get()) {
            ++count;
            return;
        }

        //spin
        while (owner.compareAndSet(null, t)) {
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
}
