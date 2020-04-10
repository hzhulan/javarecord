package com.hz.design.factory;

/**
 * @authod: pp_lan on 2020/3/18.
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
}
