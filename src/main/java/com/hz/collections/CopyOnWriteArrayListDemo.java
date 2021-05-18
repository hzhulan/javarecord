package com.hz.collections;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @file: CopyOnWriteArrayListDemo
 * @version: 1.0
 * @Description: 线程安全List
 * 参考链接：https://blog.csdn.net/weixin_42146366/article/details/88016527
 * 原理：
 * ①、CopyOnWriteArrayList 写数组的拷贝，支持高效率并发且是线程安全的, 读操作无锁的ArrayList。所有可变操作都是通过对底层数组进行一次新的复制来实现。
 * ②、CopyOnWriteArrayList 适合使用在读操作远远大于写操作的场景里，比如缓存。它不存在扩容的概念，每次写操作都要复制一个副本，在副本的基础上修改后改变Array引用。CopyOnWriteArrayList中写操作需要大面积复制数组，所以性能肯定很差。
 * ③、CopyOnWriteArrayList 适合读多写少的场景，不过这类慎用 ，因为谁也没法保证CopyOnWriteArrayList 到底要放置多少数据，万一数据稍微有点多，每次add/set都要重新复制数组，这个代价实在太高昂了。在高性能的互联网应用中，这种操作分分钟引起故障。
 * 缺点：
 * 1、由于写操作的时候，需要拷贝数组，会消耗内存，如果原数组的内容比较多的情况下，可能导致young gc或者full gc。
 * （1、young gc ：年轻代（Young Generation）：对象被创建时，内存的分配首先发生在年轻代（大对象可以直接被创建在年老代），大部分的对象在创建后很快就不再使用，因此很快变得不可达，于是被年轻代的GC机制清理掉（IBM的研究表明，98%的对象都是很快消亡的），这个GC机制被称为Minor GC或叫Young GC。
 * 2、年老代（Old Generation）：对象如果在年轻代存活了足够长的时间而没有被清理掉（即在几次Young GC后存活了下来），则会被复制到年老代，年老代的空间一般比年轻代大，能存放更多的对象，在年老代上发生的GC次数也比年轻代少。当年老代内存不足时，将执行Major GC，也叫 Full GC
 * ）
 *
 * 2、不能用于实时读的场景，像拷贝数组、新增元素都需要时间，所以调用一个set操作后，读取到数据可能还是旧的,虽然CopyOnWriteArrayList 能做到最终一致性,但是还是没法满足实时性要求；
 * @Author: pp_lan
 * @Date: 2021/5/18
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        try {
            pool.submit(() -> {
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                }
            });
            pool.submit(() -> {
                for(Iterator it = list.iterator(); it.hasNext();) {
                    System.out.println(it.next());
                }
            });
        } finally {
            try {
                pool.shutdown();
            } catch (Exception e) {
                pool.shutdownNow();
            }
        }


    }
}
