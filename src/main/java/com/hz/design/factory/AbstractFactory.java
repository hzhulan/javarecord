package com.hz.design.factory;

/**
 * @author: pp_lan on 2020/3/18.
 */
public class AbstractFactory {
    interface Car {
        void driver();
    }

    class Benz implements Car {

        @Override
        public void driver() {
            System.out.println("Benz driving");
        }
    }

    class BMW implements Car {

        @Override
        public void driver() {
            System.out.println("Benz driving");
        }
    }

    interface CarFactory {
        Car create();
    }

    class BenzFactory implements CarFactory {

        @Override
        public Car create() {
            return new Benz();
        }
    }


    class BMWFactory implements CarFactory {

        @Override
        public Car create() {
            return new BMW();
        }
    }

    public CarFactory getCarFactory(int type) {
        switch (type) {
            case 1:
                return new BenzFactory();
            case 2:
                return new BMWFactory();
            default:
                throw new RuntimeException("该类型工厂暂不支持");
        }

    }

    public static void main(String[] args) {
        CarFactory carFactory = new AbstractFactory().getCarFactory(1);
        Car car = carFactory.create();
        car.driver();
    }
}
