package com.hz.design.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @authod: pp_lan on 2020/3/20.
 * 原型模式：浅复制、深复制
 */
public class ProtoType {

    /**
     * 深复制 实现序列化接口Serializable
     * @param t
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static  <T> T deepCopy(T t) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oops = new ObjectOutputStream(baos);
        oops.writeObject(t);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        return (T) ois.readObject();

    }


    public static void write2File(Object t) throws IOException {
        FileOutputStream fos = new FileOutputStream("serial.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(t);
        oos.close();
    }

    public static Object readFromFile(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        Object o = ois.readObject();
        return o;
    }

    /**
     * 浅复制 实现Cloneable接口
     * @param car
     * @return
     * @throws CloneNotSupportedException
     */
    public static Car clone (Car car) throws CloneNotSupportedException {
        return car.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Car car = new Car("奔驰");
//        Car clone = clone(car);
//        Car deepCopy = deepCopy(car);
//
//        clone.setName("宝马");
//        clone.getList().add("真皮座椅");
//
//        deepCopy.setName("雷克萨斯");
//        deepCopy.getList().add("安全气囊");
//
//        System.out.println(car.getName());
//        System.out.println(clone.getName());
//        System.out.println(deepCopy.getName());
//
//        System.out.println("=============");
//
//        System.out.println(car.getList());
//        System.out.println(clone.getList());
//        System.out.println(deepCopy.getList());

//        write2File(car);

        Car o = (Car)readFromFile("serial.txt");
        System.out.println(o);
    }

}


class Car implements Cloneable, Serializable {

    private static final long serialVersionUID = 5211271313372307580L;

    private String name;

    private List<String> list;

    public Car(String name) {
        this.name = name;
        this.list = new ArrayList<>();
        list.add("轮胎");
        list.add("车身");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    protected Car clone() throws CloneNotSupportedException {
        return (Car) super.clone();
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
