package com.hz.design.bridge;

/**
 * @authod: pp_lan on 2020/3/23.
 */
public class BrigeDemo {

    interface Car {
        void drive();
    }

    class Jeep implements Car {

        @Override
        public void drive() {
            System.out.println("驾驶Jeep出发");
        }
    }

    abstract class Person {

        String name;

        private Car car;

        public Person(String name, Car car) {
            this.car = car;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void driveCar() {
            this.car.drive();
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    class Man extends Person {

        public Man(String name, Car car) {
            super(name, car);
        }

    }

    public static void main(String[] args) {

    }

}
