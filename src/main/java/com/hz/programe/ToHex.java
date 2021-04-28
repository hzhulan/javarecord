package com.hz.programe;

import java.util.Scanner;

/**
 * @author: pp_lan on 2020/3/12.
 */
public class ToHex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();

            System.out.println(Integer.decode(str));


//            if (!str.startsWith("0x")) {
//                System.out.println("error");
//            } else {
//                String substring = str.substring(2);
//                System.out.println(toHex(substring));
//            }
        }
    }

    public static int toHex(String value) {
        char[] chars = value.toCharArray();
        int result = 0;
        int bit;
        int pos = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            bit = (int) Math.pow(16, pos++);
            result += toNum(chars[i]) * bit;
        }
        return result;
    }

    public static int toNum(char c) {
        try {
            return Integer.parseInt(String.valueOf(c));
        } catch (NumberFormatException e) {
            String s = String.valueOf(c).toLowerCase();
            c = s.toCharArray()[0];
            return (int)c-87;
        }

    }
}
