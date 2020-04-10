package com.hz.collections;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @authod: pp_lan on 2020/4/8.
 */
public class BlockingQueue {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
//        System.out.println(queue.add("1"));
//        System.out.println(queue.add("2"));
//        System.out.println(queue.add("3"));
//        System.out.println(queue.add("4"));


        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d"));
    }
}
