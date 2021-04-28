package com.hz.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: pp_lan on 2020/3/20.
 * jdk动态代理
 */
public class JdkProxy implements InvocationHandler {
    private Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        Object proxy = Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("【JDK代理】准备开始");
        Object invoke = method.invoke(target, args);
        System.out.println("【JDK代理】执行结束");
        return invoke;
    }

    public static void main(String[] args) {
        Car proxy = (Car)new JdkProxy(new Benz()).getProxy();
        proxy.drive();
    }

    interface Car {
        void drive();
    }

    static class Benz implements Car {
        @Override
        public void drive() {
            System.out.println("Benz driving");
        }
    }
}
