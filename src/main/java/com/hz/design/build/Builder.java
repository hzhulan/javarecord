package com.hz.design.build;

/**
 * @author: pp_lan on 2020/3/19.
 */
public abstract class Builder {

    protected Product product = new Product();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    public Product getResult () {
        return this.product;
    }

}
