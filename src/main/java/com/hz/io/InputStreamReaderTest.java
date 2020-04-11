package com.hz.io;

import org.junit.Test;

import java.io.*;

/**
 * @Description 转换流
 * 1. 转换流(属于字符流)
 *  InputStreamReader
 *  OutputStreamWriter
 * 2. 提供字节流和字符流之间的转换
 * @Date 2020/4/3 17:06
 * @Created by CZB
 */
public class InputStreamReaderTest {

    public static void main(String[] args) {
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream("hello1.txt"), "utf-8");
            char[] chars = new char[3];
            int len;
            while ((len = isr.read(chars)) != -1) {
                String string = new String(chars, 0, len);
                System.out.println(string);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test02() {
        File file1 = new File("hello1.txt");
        File file2 = new File("hello1_gbk.txt");
    }
}
