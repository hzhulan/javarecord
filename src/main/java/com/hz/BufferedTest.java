package com.hz;

import org.junit.Test;

import java.io.*;

/**
 * @Description 缓冲流
 * 提高读写速度原因：内部提供了缓冲区默认大小8192
 * @Date 2020/4/3 15:40
 * @Created by CZB
 */
public class BufferedTest {

    @Test
    public void testBufferedStream() {
        File srcFile = new File("F:\\文档\\交接-黄杰\\交接文档-代码类.docx");
        File destFile = new File("b.docx");

        long start = System.currentTimeMillis();
        bufferedStreamTest(srcFile,destFile);
        long end = System.currentTimeMillis();

        System.out.println("拷贝时间:" + (end - start));
    }

    public void bufferedStreamTest(File srcFile, File destFile) {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);

                // 主动清空缓冲区默认大小8192， 满了一次性传输。flush的时候会主动传输
//                bos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源关闭， 先关闭外层流，在关闭内层流

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    public void copyBufferedWriter() {
        long start = System.currentTimeMillis();
        File file1 = new File("hello1.txt");
        File file2 = new File("hello3.txt");
        copyBufferedWriter(file1, file2);
        long end = System.currentTimeMillis();

        System.out.println("拷贝时间" + (end - start));
    }

    public void copyBufferedWriter(File srcFile, File destFile) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(srcFile));
            bw = new BufferedWriter(new FileWriter(destFile));

            String data;

            while ((data = br.readLine()) != null) {
                bw.write(data);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
