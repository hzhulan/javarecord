package com.hz.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @file: CollectionSynchronizedListDemo
 * @version: 1.0
 * @Description: 线程安全List
 * @Author: pp_lan
 * @Date: 2021/5/21
 */
public class CollectionSynchronizedListDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        try {
            List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
            for (int i = 0; i < 100; i++) {
                pool.submit(() -> {
                    if (list.size() > 0) {
                        Integer integer = list.get(list.size() - 1);
                        list.add(integer + 1);
                    } else {
                        list.add(1);
                    }
                });
            }
            System.out.println(list);
        } finally {
            // 简单关闭
            pool.shutdown();
        }
    }
}
