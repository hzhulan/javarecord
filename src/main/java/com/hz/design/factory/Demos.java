package com.hz.design.factory;

import com.hz.design.singleton.SingleTon2;

/**
 * @authod: pp_lan on 2020/3/19.
 */
public class Demos {
    public static void main(String[] args) {
        SingleTon2 instance = SingleTon2.getInstance();
        System.out.println(instance);
    }
}
