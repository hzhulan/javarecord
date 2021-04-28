package com.hz.programe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: pp_lan on 2020/3/26.
 */
public class IncrTest {
    public static void concurrentTest() throws InterruptedException {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final CountDownLatch countDownLatch = new CountDownLatch(1000);
        final CountDownLatch countDownLatch2 = new CountDownLatch(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    for (int j = 0; j < 1000; j++) {
                        atomicInteger.incrementAndGet();
                    }


                    countDownLatch2.countDown();
                }
            });
            countDownLatch.countDown();
        }

        countDownLatch2.await();
        System.out.println(atomicInteger);
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        concurrentTest();
    }
}
