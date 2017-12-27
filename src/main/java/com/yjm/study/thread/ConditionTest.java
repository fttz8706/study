package com.yjm.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] a) {

        final Lock lock = new ReentrantLock();

        final Condition c1 = lock.newCondition();

        final Condition c2 = lock.newCondition();

        Thread t1 = new Thread(){
            public void run() {

                try {
                    lock.lock();
                    c1.await();
                    c2.await();
                    System.out.println("t1 end");
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                try {
                    lock.lock();
                    c1.signal();
                    System.out.println("t2 end");
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread t3 = new Thread(){
            public void run(){
                try {
                    lock.lock();
                    c2.signal();
                    System.out.println("t3 end");
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();

    }
}
