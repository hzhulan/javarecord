package com.hz.programe;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author: pp_lan on 2020/4/2.
 */
public class ListDemo {
    public static void main(String[] args) {
        char[] chars = "hello".toCharArray();
        char[] chars1 = Arrays.copyOf(chars, chars.length + 1);
        for (char c : chars1) {
            System.out.println("value:\t"  + c);
        }

//        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();


        HashSet<Integer> set = new HashSet<>();

    }
}
