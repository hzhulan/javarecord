package com.hz.problems;

import org.junit.Test;

/**
 * @authod: pp_lan on 2020/4/14.
 * 原文链接: http://www.yinwang.org/blog-cn/2020/02/13/java-type-system
 */
public class String2ObjectArray {

    @Test
    /**
     * 运行结果：
     * java.lang.ArrayStoreException: java.lang.Integer
     *
     * 	at com.hz.problems.String2ObjectArray.test01(String2ObjectArray.java:17)
     *
     * 	new String[2]先分配了内存空间，创建了对象的数组对象
     * 	Object[] b = a; 只是相当于修改了引用  换了个马甲而已
     * 	因此赋值掐类型会报错
     */
    public void test01() {

        String[] a = new String[2];
        Object[] b = a;
        a[0] = "hi";
        b[1] = Integer.valueOf(42);
    }

    @Test
    public void test02() {

        Object[] a = new String[2];
        a[0] = "hi";
        a[1] = Integer.valueOf(42);
    }

}
