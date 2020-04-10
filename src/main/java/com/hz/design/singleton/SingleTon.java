package com.hz.design.singleton;

/**
 * @authod: pp_lan on 2020/3/19.
 * 常见的为double-check + volatile 实现
 * 此处使用 静态类 实现
 */
public class SingleTon {
    private static SingleTon instance;

    private SingleTon () {

    }

    public SingleTon getInstance() {
        return Holder.instance;
    }

    static class Holder {
        private static SingleTon instance = new SingleTon();
    }
}
