package com.yjm.study.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    private Object lock = new Object();
    private int count = 0;
    private static final AtomicInteger seq = new AtomicInteger(0);

    /**
     * 线程不安全
     * 得到的值会比期望的小
     * @throws Exception
     */
    public void unSafeTest() throws Exception {
        final CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i<100; i++) {
            new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j<10000; j++) {
                        count++;
                    }
                    latch.countDown();
                }
            }.start();
        }
        latch.await();
        System.out.println(count);
    }

    /**
     * 线程安全
     * 用锁实现
     * @throws Exception
     */
    public void safeTest() throws Exception {
        final CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i<100; i++) {
            new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j<10000; j++) {
                        synchronized (lock) {
                            count++;
                        }
                    }
                    latch.countDown();
                }
            }.start();
        }
        latch.await();
        System.out.println(count);
    }

    public void atomicIncre() throws Exception {
        final CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i<100; i++) {
            new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j<10000; j++) {
                        seq.incrementAndGet();
                    }
                    latch.countDown();
                }
            }.start();
        }
        latch.await();
        System.out.println(seq.get());
    }

    public int increase() {
        return seq.incrementAndGet();
    }

    public static int next() {
//        int i = 0;
        while(true) {
//            i++;
            int current = seq.get();
            int next = current + 1;
            if (seq.compareAndSet(current, next)) {
                // i越大说明线程竞争越激烈
//                if (i > 100) {
//                    System.out.println(Thread.currentThread().getName());
//                }
                return current;
            }
        }
    }

    public void testNext() throws Exception {
        final CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i<100; i++) {
            new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j<30000; j++) {
                        next();
                    }
                    latch.countDown();
                }
            }.start();
        }
        latch.await();
        System.out.println(seq.get());
    }


    public static void main(String[] a) throws Exception {
//        new Atomic().incre();
//        new Atomic().atomicIncre();
        new Atomic().testNext();
    }
}
