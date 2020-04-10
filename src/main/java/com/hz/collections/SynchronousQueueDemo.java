package com.hz.collections;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @authod: pp_lan on 2020/4/8.
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(()-> {
            try {
                System.out.println("========1消费=======");
                queue.put("1");
                System.out.println("========2消费=======");
                queue.put("2");
                System.out.println("========3消费=======");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(()->{
            try {
                String value = queue.take();
                System.out.println(value);
                TimeUnit.SECONDS.sleep(2);
                String value2 = queue.take();
                System.out.println(value2);
                String take3 = queue.take();
                System.out.println(take3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
