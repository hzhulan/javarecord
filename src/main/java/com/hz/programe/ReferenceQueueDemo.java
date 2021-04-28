package com.hz.programe;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 使用引用队列，在清除前，放入队列中
 * @author: pp_lan on 2020/4/9.
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1, queue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(queue.poll());

        System.out.println("===============");
        o1 = null;
        System.gc();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(queue.poll());
    }
}
