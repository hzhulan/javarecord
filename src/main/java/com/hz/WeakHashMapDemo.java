package com.hz.reference;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @authod: pp_lan on 2020/4/9.
 */
public class WeakHashMapDemo {

    @Test
    public void test01() {
        Integer key = new Integer(1);
        Map<Integer, String> map = new HashMap<>();
        map.put(key, "1");

        key = null;

        System.gc();
        System.out.println(map);
    }

    @Test
    public void test02() {
        Integer key = new Integer(2);
        Map<Integer, String> map = new WeakHashMap<>();
        map.put(key, "2");

        key = null;

        System.gc();
        System.out.println(map);
    }

    @Test
    public void test03() {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);

        o = null;
        System.gc();

        Object o1 = weakReference.get();
        System.out.println(o1);
    }

}
