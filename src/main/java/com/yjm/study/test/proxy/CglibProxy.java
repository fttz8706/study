package com.yjm.study.test.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 实现动态代理
 * Created by test on 16/5/26.
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public CglibProxy(Object target){
        this.target = target;
    }

    public Object getInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable{
        this.before();
        Object result = proxy.invokeSuper(obj, args);
        this.after();
        return result;
    }

    private void before(){
        System.out.println("before invoke real method...");
    }

    private void after(){
        System.out.println("after invoke real method...");
    }
}
