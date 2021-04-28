package com.hz.programe;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author: pp_lan on 2020/3/12.
 */
public class SortDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Set<Integer> set = new TreeSet<>();
            int num = scanner.nextInt();
            if (num > 0) {
                for (int i = 0; i < num; i++) {
                    set.add(scanner.nextInt());
                }
            }
            for (Integer integer : set) {
                System.out.println(integer);
            }
        }

    }

//    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        while(sc.hasNext()){
//
//            TreeSet<Integer> set=new TreeSet<Integer>();
//            int n=sc.nextInt();
//            if(n>0){
//                for(int i=0;i<n;i++){
//                    set.add(sc.nextInt());
//                }
//            }
//            for(Integer i:set){
//                System.out.println(i);
//            }
//        }
//    }
}
