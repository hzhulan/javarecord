package com.hz.design.proxy;

/**
 * @authod: pp_lan on 2020/3/20.
 */
public class StaticProxy {
    interface Car {
        void drive();
    }

    static class Benz implements Car {
        @Override
        public void drive() {
            System.out.println("benz driving");
        }
    }

    static class ProxyManager implements Car {

        private Car car;

        public ProxyManager(Car car) {
            this.car = car;
        }

        @Override
        public void drive() {
            System.out.println("【代理】准备工作");
            car.drive();
            System.out.println("【代理】结束工作");
        }
    }

    public static void main(String[] args) {
        ProxyManager proxy = new ProxyManager(new Benz());
        proxy.drive();
    }
}
