package com.hz.design.serialization;

import java.io.*;

/**
 * @author: pp_lan on 2020/3/23.
 */
public class SerializationUtil {

    public static void serialize(Object o, String fileName) throws IOException {
        FileOutputStream fops = new FileOutputStream(fileName);

        ObjectOutputStream oops = new ObjectOutputStream(fops);

        oops.writeObject(o);

        fops.close();

    }

    public static Object deserialize(String  fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);

        ObjectInputStream ois = new ObjectInputStream(fis);

        Object o = ois.readObject();
        return o;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Car car = new Car();
//        System.out.println(car);
////
//        serialize(car, "123");
        Car car2 = (Car)deserialize("123");
        System.out.println(car2);


    }

    static class Car implements Serializable {

        private static final long serialVersionUID = -6391932686991691424L;

        private int num;

        private static String name = "car";

        private int age;

        public Car() {
            this.num = (int ) (Math.random() * 1000);
            this.age = 10;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "num=" + num +
                    ", age=" + age +
                    ", name=" + name +
                    '}';
        }
    }
}
