package com.yjm.study.proxy;

import com.yjm.study.proxy.impl.TargetServiceImpl;
import org.junit.Test;

/**
 * 代理测试类
 * Created by yangjinming on 16/5/25.
 */
public class ProxyTest {

    @Test
    public void staticProxyTest(){
        TargetService target = new TargetServiceImpl();
        StaticProxy proxy = new StaticProxy(target);
        proxy.doInvoke();
    }
}
