package com.hz.leaksuspect;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @file: LeakSuspectDemo
 * @version: 1.0
 * @Description: 内存泄漏分析
 * @Author: pp_lan
 * @Date: 2021/5/10
 */
public class LeakSuspectDemo {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        LeakSuspectDemo demo = new LeakSuspectDemo();
        List<Car> list = new ArrayList<>();
        while (true) {
            list.add(demo.new Car());
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    public class Car {
        public Car() {
            count.getAndIncrement();
            System.out.println(String.format("car对应存活数量%d", count.get()));
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            count.decrementAndGet();
        }
    }
}
