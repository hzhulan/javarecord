package com.hz.design.factory;

/**
 * @authod: pp_lan on 2020/3/18.
 * 职责单一，只负责生产单一产品。
 */
public class FactoryDemo {

    public interface CarFactory {
        Car createCar();
    }



    class BenzFactory implements CarFactory {

        @Override
        public Car createCar() {
            return new Benz();
        }
    }
}
