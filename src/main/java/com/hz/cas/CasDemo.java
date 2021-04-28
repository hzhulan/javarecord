package com.hz.cas;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: pp_lan on 2020/4/2.
 * CAS样例
 * 缺点：
 * 1. 循环时间长，开销大（CPU负荷）
 * 2. 只能保证单个共享变量的原子操作
 * 3. ABA问题，通过版本问题解决
 */
public class CasDemo {

    @Test
    /**
     * Cas样例
     */
    public void test01() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        AtomicInteger atomicInteger = new AtomicInteger(5);

        CountDownLatch latch = new CountDownLatch(2);

        //compareAndSet，expert期望值及输入值，如果值被修改了就和expert不一致，无法修改

        executor.submit(() -> {

            try {
                TimeUnit.MILLISECONDS.sleep((long)(Math.random()*200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicInteger.compareAndSet(5, 2019);
            System.out.println("2019\t" + b);
            latch.countDown();
        });
        executor.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep((long)(Math.random()*200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicInteger.compareAndSet(5, 1024);
            System.out.println("1024更新状态\t" + b);
            latch.countDown();
        });

        //阀门等待线程执行完毕
        latch.await();
        System.out.println("value============= " + atomicInteger.get());
    }

    @Test
    /**
     * 原子引用
     */
    public void test02() {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User user1 = new User("zhangsan", 23);
        User user2 = new User("lisi", 24);
        atomicReference.set(user1);

        System.out.println(atomicReference.compareAndSet(user1, user2) + "\t" + atomicReference.get());

        //无法更新
        System.out.println(atomicReference.compareAndSet(user1, user1) + "\t" + atomicReference.get());

    }


    /**
     * Junit中使用sleep会不运行，此处改为main方法测试号
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("================= ABA问题复现 =====================");
        CountDownLatch latch1 = new CountDownLatch(2);
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t执行");
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
            latch1.countDown();
        }, "thread1").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicReference.compareAndSet(100, 2019);

            System.out.println("最新value：\t" + atomicReference.get());
            latch1.countDown();
        }, "thread2").start();
        latch1.await();
        System.out.println("================= ABA问题复现结束 =====================");

        System.out.println("================= ABA问题解决开始 =====================");
        CountDownLatch latch2 = new CountDownLatch(2);
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            atomicStampedReference.compareAndSet(100, 101,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(101, 102,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(102, 100,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);


            latch2.countDown();
        }, "thread1").start();
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("使用版本号\t" + stamp);
            System.out.println("实际版本号\t" + atomicStampedReference.getStamp());

            boolean b = atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1);
            System.out.println("旧版本号执行结果\t" + b);

            latch2.countDown();
        }, "thread2").start();

        latch2.await();
        System.out.println("执行结果:\t" + atomicStampedReference.getReference());
        System.out.println("================= ABA问题解决结束 =====================");
    }

    class User {
        private String username;

        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
