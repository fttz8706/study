package com.yjm.study.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * Created by test on 16/5/25.
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object targer){
        this.target = targer;
    }

    public Object getInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException{
        this.before();
        Object result = method.invoke(target, args);
        this.after();
        return result;
    }

    private void before(){
        System.out.println("invoke before...");
    }

    private void after(){
        System.out.println("invoke after...");
    }
}
