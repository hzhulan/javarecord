package com.hz.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author: pp_lan on 2020/4/7.
 */
public class ChannelTest {

    private static final String SRCPATH = "C:\\resource\\document\\证件照\\5k\\朱辉.jpg";

    @Test
    public void test01() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream(SRCPATH);
            fos = new FileOutputStream("a.jpg");

            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(1024);

            while (inChannel.read(buf) != -1) {
                buf.flip();

                outChannel.write(buf);

                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outChannel != null) {
                    outChannel.close();
                }
                if (inChannel != null) {
                    inChannel.close();
                }
                if (fos != null) {

                    fos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    @Test
    /**
     * 直接对缓冲区进行读写操作
     */
    public void test02() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get(SRCPATH), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("channel.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();
    }

    @Test
    /**
     * 通道之间的间数据传输
     */
    public void test03() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get(SRCPATH), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("channe3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);

        inChannel.close();
        outChannel.close();

    }

    @Test
    public void test04() throws Exception{
        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");

        FileChannel channel1 = raf1.getChannel();

        ByteBuffer buf1 = ByteBuffer.allocate(1024);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        ByteBuffer[] buffers = {buf1, buf2};
        channel1.read(buffers);

        for (ByteBuffer buffer : buffers) {
            buffer.flip();
        }
//
//        System.out.println(new String(buffers[0].array(), 0, buffers[0].limit()));
//        System.out.println("-------------");
//        System.out.println(new String(buffers[1].array(), 0, buffers[1].limit()));


        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(buffers);

        channel2.close();
        channel1.close();
    }

    @Test
    public void test05() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");
        RandomAccessFile raf2 = new RandomAccessFile("13.txt", "rw");

        FileChannel channel1 = raf1.getChannel();
        FileChannel channel2 = raf2.getChannel();

        channel1.transferTo(0, channel2.size(), channel2);

    }
}
