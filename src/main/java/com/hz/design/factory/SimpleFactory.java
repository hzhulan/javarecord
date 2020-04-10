package com.hz.design.factory;

/**
 * @authod: pp_lan on 2020/3/18.
 * 简单工厂模式，根据出入的参数判断，生成对应的类
 */
public class SimpleFactory {

    public Car getCar(String name) {
        if ("benz".equals(name)) {
            return new Benz();
        } else {
            return new BMW();
        }

    }

}
