package com.yjm.study.proxy;

/**
 * 静态代理
 * Created by yangjinming on 16/5/25.
 */
public class StaticProxy {

    private TargetService target;

    public StaticProxy(TargetService target){
        this.target = target;
    }

    /**
     * 执行代理,调用目标方法之前或之后都可以添加逻辑,比如打印日志/添加监控等
     */
    public void doInvoke(){
        this.before();
        target.doInvoke();
        this.after();
    }

    private void before(){
        System.out.println("before invoke target method.");
    }

    private void after(){
        System.out.println("after invoke target method.");
    }
}
