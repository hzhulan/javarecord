package com.hz.design.singleton;

/**
 * @author: pp_lan on 2020/3/19.
 * 使用enum辅助实现
 */
public class SingleTon2 {

    private SingleTon2() {
    }

    public static SingleTon2 getInstance() {
        return EnumHolder.INSTANCE.singleTon;
    }

    private enum EnumHolder {
        INSTANCE;

        private SingleTon2 singleTon;

        EnumHolder() {
            this.singleTon = new SingleTon2();
        }
    }
}
