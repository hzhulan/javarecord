package com.hz.design.adapter;

/**
 * @authod: pp_lan on 2020/3/23.
 * 班级节目：男孩唱歌、女孩跳舞
 */
interface Student {

    void sing();

    void dance();

}

class Male implements Student {

    @Override
    public void sing() {
        System.out.println("【男生节目】唱歌");
    }

    @Override
    public void dance() {
        System.out.println("【男孩不跳舞】");
    }
}

class Female implements Student {
    @Override
    public void sing() {
        System.out.println("【女孩不唱歌】");
    }

    @Override
    public void dance() {
        System.out.println("【女孩节目】跳舞");
    }
}

public class StudentAdapt {
    private Student std;
    public StudentAdapt(String sex) {
        if ("male".equalsIgnoreCase(sex)) {
            std = new Male();
        } else {
            std = new Female();
        }
    }

    public void perform() {
        if (std instanceof Male) {
            std.sing();
        } else {
            std.dance();
        }
    }

    public static void main(String[] args) {
        StudentAdapt male = new StudentAdapt("male");
        male.perform();
        StudentAdapt female = new StudentAdapt("female");
        female.perform();
    }

}


