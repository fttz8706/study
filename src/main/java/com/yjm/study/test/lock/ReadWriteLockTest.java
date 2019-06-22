package com.yjm.study.test.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁,允许读读并发,写写和读写都不可以并发
 * synchronized的读读/读写/写写都不可以并发
 *
 * Created by test on 16/6/5.
 */
public class ReadWriteLockTest {

    private Object data;
    private ReadWriteLock readWriteLock;

    public ReadWriteLockTest(){
        readWriteLock = new ReentrantReadWriteLock();
    }

    public void get(){
        readWriteLock.readLock().lock(); // 开启读锁,可以并发读,但不可读和写并发,所以读也要加锁
        try{
            System.out.println(Thread.currentThread().getName() + " get: " + data);
            Thread.sleep(1);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }

    public void set(Object data){
        readWriteLock.writeLock().lock(); // 开启写锁,任何时刻只能有一个线程进入,不能并发写
        try{
            System.out.println(Thread.currentThread().getName() + " set...");
            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] a){
        final ReadWriteLockTest lockTest = new ReadWriteLockTest();

        for(int i = 0; i < 1; i++){
            new Thread(new Runnable() {
                public void run() {
                    lockTest.set(new Object());
                }
            }).start();
        }

        for(int i = 0; i < 1; i++){
            new Thread(new Runnable(){
                public void run(){
                    lockTest.get();
                }
            }).start();
        }
    }

}
