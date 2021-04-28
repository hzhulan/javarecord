package com.hz.design.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: pp_lan on 2020/3/20.
 */
public class CglibProxy<T> {

    private Enhancer enhancer;

    public CglibProxy(Class<T> clazz) {
        this.enhancer = new Enhancer();
        this.enhancer.setSuperclass(clazz);
        this.enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("【cglib代理】开始");
                Object t = methodProxy.invokeSuper(o, objects);
                System.out.println("【cglib代理】结束");
                return t;
            }
        });
    }

    public T getProxy () {
        return (T)this.enhancer.create();
    }

    public static void main(String[] args) {
        CglibProxy<Benz> cglib = new CglibProxy<>(Benz.class);
        Benz proxy = cglib.getProxy();
        proxy.drive();
    }


    static class Benz{
        public void drive() {
            System.out.println("Benz driving");
        }
    }
}
