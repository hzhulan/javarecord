package com.hz.programe;

/**
 * @author: pp_lan on 2020/3/26.
 */
public class CountDownLatchDemo {

    static abstract class Latch {
        protected int limit;

        public Latch(int limit) {
            this.limit = limit;
        }

        public abstract void await() throws InterruptedException;

        public abstract void countDown();

        public abstract int getUnarrived();
    }

    static class CountDownLatch extends Latch {

        public CountDownLatch(int limit) {
            super(limit);
        }

        @Override
        public void await() throws InterruptedException {
            synchronized (this) {
                while (limit > 0) {
                    this.wait();
                }
            }
        }

        @Override
        public void countDown() {
            synchronized (this) {
                if (limit <= 0) {
                    System.out.println("任务结束");
                } else {
                    limit --;
                }
                this.notifyAll();
            }
        }

        @Override
        public int getUnarrived() {
            synchronized (this) {
                return this.limit;
            }
        }
    }

    public static class Task implements Runnable {
        private CountDownLatch latch;

        public Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + "\t任务进行结束");
            System.out.println(latch.getUnarrived());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);

        for (int i = 0; i < 3; i++) {
            new Thread(new Task(latch)).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Task(latch)).start();
        }
        latch.await();

    }
}
