package com.hz.programe;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @authod: pp_lan on 2020/3/13.
 */
public class ScoreDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] info = line.split(" ");
            int num = Integer.parseInt(info[0]);
            int optTimes = Integer.parseInt(info[1]);

            String[] scores = sc.nextLine().split(" ");
            int[] score = new int[num];
            for (int i=0; i< num; i++) {
                score[i] = Integer.parseInt(scores[i]);
            }

            StringBuilder sb = new StringBuilder();
            ArrayList<Integer> result = new ArrayList<>();
            for (int i=0; i< optTimes; i++) {

                String[] optStr = sc.nextLine().split(" ");
                if ("U".equalsIgnoreCase(optStr[0])) {
                    int index = Integer.parseInt(optStr[1]);
                    score[index - 1] = Integer.parseInt(optStr[2]);
                } else if ("Q".equalsIgnoreCase(optStr[0])) {
                    int a = Integer.parseInt(optStr[1]);
                    int b = Integer.parseInt(optStr[2]);
                    int beginIndex = a <= b ? a : b;
                    int endIndex = a > b ? a : b;
                    int big = -999;
                    for (int j = beginIndex - 1; j < endIndex; j++) {
                        if (score[j] >= big) {
                            big = score[j];
                        }
                    }
                    result.add(big);
                }
            }
            for (Integer i : result) {
                System.out.println(i);
            }
        }
    }
}
