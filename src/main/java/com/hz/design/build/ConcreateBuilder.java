package com.hz.design.build;

/**
 * @author: pp_lan on 2020/3/19.
 */
public class ConcreateBuilder extends Builder {
    @Override
    public void buildPartA() {
        this.product.setPartA("建造A部分");
        System.out.println();
    }

    @Override
    public void buildPartB() {
        this.product.setPartB("建造B部分");
    }

    @Override
    public void buildPartC() {
        this.product.setPartC("建造C部分");
    }

    @Override
    public Product getResult() {
        buildPartA();
        buildPartB();
        buildPartC();
        return super.getResult();
    }
}
