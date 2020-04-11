package com.hz.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @Description 用来处理基本数据类型
 * 读取不同类型的基本数据要与写入的顺序一致，不然回报EOFException
 * @Date 2020/4/7 10:13
 * @Created by CZB
 */
public class DataStreamTest {
    public static void main(String[] args) throws IOException {
//        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
//        dos.writeUTF("sander");
//        dos.flush();
//        dos.writeInt(123);
//        dos.flush();
//        dos.close();


        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
        String s = dis.readUTF();
        int i = dis.readInt();
        System.out.println(s);
        System.out.println(i);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
//        executorService.scheduleWithFixedDelay();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 500, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "my-thread-pool");
            }
        }, new ThreadPoolExecutor.AbortPolicy());

        for (int j = 0; j < 20; j++) {
            final int temp = j;
            pool.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "\t" + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

//        Executors.newSingleThreadExecutor();
//        Executors.newFixedThreadPool(10);
//        Executors.newCachedThreadPool();
    }
}
