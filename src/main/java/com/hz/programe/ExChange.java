package com.hz.programe;

import java.util.Scanner;

/**
 * @authod: pp_lan on 2020/3/12.
 */
public class ExChange {

    public static int exchange(int blank) {
        int total = 0;
        int direct, left;
        while (blank + 1 >= 3) {
            if (blank >= 3) {
                direct = blank / 3;
                left = blank % 3;
                total += direct;
                blank = direct + left;
            } else if (blank == 2) {
                total += 1;
                blank = 0;
            }
        }


        return total;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int blank = scanner.nextInt();
            int exchange = exchange(blank);
            System.out.println("可以换的数量为：\t" + exchange);
        }
    }
}
