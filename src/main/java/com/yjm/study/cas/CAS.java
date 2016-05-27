package com.yjm.study.cas;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by yangjinming on 16/5/27.
 */
public class CAS {

    @Test
    public void fun(){
        int a = 0, b = 0;
        if(a == b){
            // 多线程环境下,a的值可能会被改变,导致值被覆盖
            // 解决办法一是使用synchronized,一种是使用cas语义,见下方的方法
            a = b + 1;
        }
        System.out.println("a=" + a);
    }

    @Test
    public void cas(){
        int a = 0, b = 0;
        int mark = a;
        while(true) {
            if (a == b) {
                AtomicInteger atomicInteger = new AtomicInteger(a);
                // 只有当a的值和原先的值相等才做更新
                if (atomicInteger.compareAndSet(mark, b + 1)) {
                    a = atomicInteger.get();
                    break;
                }
            }
        }
        System.out.println("a=" + a);
    }

    /**
     * CSA会导致ABA问题,通过AtomicStampedReference可解决
     */
    @Test
    public void aba(){
        int a = 0, b = 0;
        int mark = a;
        AtomicStampedReference atomicStampedRef = new AtomicStampedReference(a, 0);
        int stampe = atomicStampedRef.getStamp();
        if(a == b){
            if(atomicStampedRef.compareAndSet(mark, b+1, stampe, stampe+1)){
                a = b + 1;
            }
        }
        System.out.println("a=" + a);
    }
}
