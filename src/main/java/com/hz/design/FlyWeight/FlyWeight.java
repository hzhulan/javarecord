package com.hz.design.FlyWeight;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: pp_lan on 2020/3/20.
 * 享元模式: 共享对象，直接返回在内存中已有的对象，避免重新创建。
 */
public class FlyWeight {

    public static void main(String[] args) {
        CarFactory.Car benz1 = CarFactory.getCarByName("benz");
        CarFactory.Car benz2 = CarFactory.getCarByName("benz");
        CarFactory.Car bmw = CarFactory.getCarByName("bmw");
        System.out.println(benz1);
        System.out.println(benz2);
        System.out.println(bmw);
    }


}



class CarFactory {
    private static Map<String, Car> map = new HashMap<String, Car>();

    public static Car getCarByName(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        } else {
            Car car = new Car(name);
            map.put(name, car);
            return car;
        }
    }

    static class Car {
        private int price;

        private String name;

        private Car(String name) {
            this.name = name;
            this.price = new Random().nextInt(100);
        }

        @Override
        public String toString() {
            return "Car{" +
                    "price=" + price +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}