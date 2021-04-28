package com.hz.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: pp_lan on 2020/4/8.
 */
public class InterruptDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("线程开始");
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();

    }
}
