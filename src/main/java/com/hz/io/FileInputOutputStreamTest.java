package com.hz.io;

import org.junit.Test;

import java.io.*;

/**
 * @Description 字节流操作
 * @Date 2020/4/3 15:06
 * @Created by CZB
 */
public class FileInputOutputStreamTest {

    @Test
    public void testFileInputStream() {
        File file = new File("hello1.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[5];
            int len;

            while ((len = fis.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void copyFile(File srcFile, File destFile) {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void copyFile() {
        File srcFile = new File("F:\\文档\\交接-黄杰\\交接文档-代码类.docx");
        File destFile = new File("a.docx");

        long start = System.currentTimeMillis();

        copyFile(srcFile, destFile);

        long end = System.currentTimeMillis();


        System.out.println("拷贝时间:\t" + (end - start));
    }
}
