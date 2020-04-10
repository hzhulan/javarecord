package com.hz;

import org.junit.Test;

import java.io.*;

/**
 * @Description 字符流操作demo
 * @Date 2020/4/3 13:36
 * @Created by CZB
 */
public class FileReaderWriterTest {

    @Test
    public void test01() {
        FileReader fileReader = null;
        try {
            // 1. 实例化File对象
            File file = new File("hello1.txt");
            // 2. 提供具体的流
            fileReader = new FileReader(file);
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char)data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 3. 数据的读入
            try {
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test02() {
        FileReader fileReader = null;
        try {
            // 1. 实例化File对象
            File file = new File("hello1.txt");
            // 2. 提供具体的流
            fileReader = new FileReader(file);

            char[] temp = new char[5];
            //返回每次读入到数组中的字符个数。如果达到文件末尾返回-1
            int len;
            while ((len = fileReader.read(temp)) != -1) {
//                for (int i = 0; i < len; i++) {
//                    System.out.print(temp[i]);
//                }

                String s = new String(temp, 0, len);
                System.out.print(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 3. 数据的读入
            try {
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    /**
     * 数据写出
     */
    public void testWrite() throws IOException {
        // 1. 提供file对象
        File file = new File("hello1.txt");


        // 2. FileWriter对象
        FileWriter write = new FileWriter(file, true);

        //3. 写出操作
        write.write("I have a dream!\n");
        write.write("U need one too");

        //4. 关闭操作
        write.close();
    }

    @Test
    public void testFileReaderFileWriter() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            File srcFile = new File("hello1.txt");
            File destFile = new File("hello2.txt");

            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                fw.write(new String(cbuf, 0, len));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
