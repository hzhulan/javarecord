package com.hz.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 缓冲区 底层数组，用于存储不同类型的数据
 *
 * 根据数据类型的不同（Boolean除外），提供了相应内容的缓冲区
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区管理方式都是通过allocation()分配缓冲区大小
 * get() 存
 * put() 取
 * capacity: 容量
 * limit： 界限 缓冲区中可以操作数据的大小
 * position： 位置 缓冲区中正在操作数据的位置
 *
 * position <= limit <= capacity
 *
 *
 * mark：记录当前position的位置。通过reset() 恢复到mark的位置。
 * @authod: pp_lan on 2020/4/7.
 */
public class TestBuffer {
    @Test
    public void test01() {
        // 1. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);


        // 2. 存数据到缓冲区
        System.out.println("============= put ===========");
        buf.put("abcde".getBytes());

        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println("============= flip ===========");
        buf.flip();
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //可重读数据
        System.out.println("============= rewind() ===========");
        buf.rewind();

        // 情空缓冲区 数据被遗忘
        System.out.println("============= 清空缓冲区 ===========");
        buf.clear();
    }

    @Test
    public void test02(){
        String str = "abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());

        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buf.position());


        buf.mark();

        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buf.position());

        buf.reset();

        System.out.println("重置后的position" + buf.position());
    }

    @Test
    public void test03() {
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        boolean direct = buf.isDirect();

    }
}
