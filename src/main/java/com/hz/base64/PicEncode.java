package com.hz.base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @authod: pp_lan on 2020/5/10.
 */
public class PicEncode {

    /**
     * 本地图片转换Base64的方法
     * 参考： https://blog.csdn.net/qq_27870421/article/details/103278761
     *
     * @param imgPath     
     */
    private static void imageToBase64(String imgPath) {
        byte[] data = null;
        try (InputStream in = new FileInputStream(imgPath)){
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回Base64编码过的字节数组字符串
        System.out.println("本地图片转换Base64: ");
        System.out.println(Base64.getEncoder().encodeToString(data));
    }


    public static void main(String[] args) {
        imageToBase64("C:\\develop\\workspace\\javarecord\\src\\main\\resources\\img\\demo2.png");
    }
}
