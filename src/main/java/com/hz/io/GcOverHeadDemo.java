package com.hz.io;

import java.util.ArrayList;
import java.util.List;

/**
 * @authod: pp_lan on 2020/4/9.
 */
public class GcOverHeadDemo {

    /**
     * -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(i++).intern());
            }
        } catch (Exception e) {
            System.out.println("********************** i:" + i);
            e.printStackTrace();
        }
    }
}
