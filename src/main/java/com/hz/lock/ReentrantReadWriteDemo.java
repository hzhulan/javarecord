package com.hz.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: pp_lan on 2020/4/3.
 */
public class ReentrantReadWriteDemo {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwlock.writeLock().lock();

        try {
            System.out.println("写入开始");
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println("写入结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwlock.writeLock().unlock();
        }


    }

    public Object get(String key) {
        rwlock.readLock().lock();
        try {
            System.out.println("读取开始");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("读取结束");
            return map.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            rwlock.readLock().unlock();
        }

    }


    public static void main(String[] args) {
        ReentrantReadWriteDemo demo = new ReentrantReadWriteDemo();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                demo.put(String.valueOf(temp), String.valueOf(temp));
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                Object o = demo.get(String.valueOf(temp));
                System.out.println("读取内容为" + o);
            }).start();
        }
    }
}
