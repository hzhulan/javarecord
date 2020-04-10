package com.hz.cb;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @authod: pp_lan on 2020/3/5.
 */
public class JdkDemo {

    @Test
    public void test01() {
        int i = 0;
        while (i < 100) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("运行中");
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test02() {
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            Thread key = entry.getKey();
            StackTraceElement[] stack = (StackTraceElement[]) entry.getValue();
            if (key.equals(Thread.currentThread())) {
                continue;
            }
            System.out.println("线程\t" + key.getName());
            for (StackTraceElement sk : stack) {
                System.out.println(sk);
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("线程1执行");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable, "线程1").start();
    }
}
