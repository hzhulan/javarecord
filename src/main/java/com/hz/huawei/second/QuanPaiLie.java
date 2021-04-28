package com.hz.huawei.second;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: pp_lan on 2020/3/18.
 */
public class QuanPaiLie {

    private static int count = 0;

    public static void permutate(String input) {
        if (input == null)
            throw new IllegalArgumentException();
        char[] data = input.toCharArray();
        permutate(data, 0);
    }

    public static void permutate(char[] data, int begin) {
        int length = data.length;
        if (begin == length) {
//            System.out.println(data);
            count++;
        }
        for (int i = begin; i < length; i++) {
            if (isUnique(data, begin, i)) {
                swap(data, begin, i);
                permutate(data, begin + 1);
                swap(data, begin, i);
            }
        }
    }

    private static boolean isUnique(char[] data, int begin, int end) {
        for (int i = begin; i < end; i++)
            if (data[i] == data[end])
                return false;
        return true;
    }

    private static void swap(char[] data, int left, int right) {
        char temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }


    private static void pailie(char[] chars, int begin) {
        if (begin == chars.length - 1) {
            System.out.println(chars);
//            print(chars, -999, begin);
            count++;
        }

        for (int i = begin; i < chars.length; i++) {
            if (isUnique(chars, begin, i))
            swap(chars, i, begin);
            pailie(chars, begin + 1);
            swap(chars, i, begin);

        }

    }


    private static void print(char[] c, int i, int begin) {
        System.out.print("[i: " + i + ", begin: " + begin + "]\t");
        for (int j = 0; j < c.length; j++) {
            System.out.print(c[j]);
        }
        System.out.println();
    }

    public static void main(String... args) {
        String str = "abcd";

//        permutate(str.toCharArray(), 0);
//        System.out.println(count);

        System.out.println(countAll(str));
    }

    public static Double countAll(String a) {
        char[] chars = a.toCharArray();
        HashMap<String, Integer> map = new HashMap<>();
        for (char c : chars) {
            if(map.containsKey(String.valueOf(c))) {
                Integer num = map.get(String.valueOf(c));
                map.put(String.valueOf(c), num + 1);
            } else {
                map.put(String.valueOf(c), 1);
            }
        }
        return cal(map);
    }

    public static Double cal(Map<String, Integer> map) {
        int length = map.size();
        double sum = 1;
        for (int i = length + 1; i > 0; i--) {
            sum *= i;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sum /= entry.getValue();
        }
        return sum;
    }
}
