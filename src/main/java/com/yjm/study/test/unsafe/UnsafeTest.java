package com.yjm.study.test.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

public class UnsafeTest {

    //Unsafe.getUnsafe()被设置为只能系统类加载器加载，AppClassloader无法加载
    //private static Unsafe unsafe = Unsafe.getUnsafe();
    private static Unsafe unsafe = get();

    public static void main(String[] a) throws Exception {

        // 获取操作系统的位数，4或8分别代表32位或64位
//        System.out.println(unsafe.addressSize());
//
//        System.out.println(System.getProperty("sun.arch.data.model"));
//
//        testGetField();
//        getObjectRefSize();

        testCountDownlatch();
    }

    // 通过反射获得Unsafe对象
    public static Unsafe get() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe)field.get(null);
        } catch (Exception e) {

        }
        return null;
    }

    public static void testGetField() throws Exception {
        Person person = new Person();
        person.setAge(1);

        Object begin = unsafe.staticFieldBase(Person.class);
        long s = unsafe.staticFieldOffset(Person.class.getDeclaredField("s"));
        long offset = unsafe.objectFieldOffset(Person.class.getDeclaredField("age"));
        int age = unsafe.getInt(person, offset);

        System.out.println(age);
    }

    // 返回一个引用所占用的长度
    public static void getObjectRefSize() {
        int objectRefSize = unsafe.arrayIndexScale(Person[].class);

        System.out.println(objectRefSize);
    }

    public static void testCountDownlatch() throws Exception{
        final int tc = 2;
        final CountDownLatch cdl = new CountDownLatch(tc);

        for(int i = 0; i < tc; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * 10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("i am " + Thread.currentThread().getName());
                    cdl.countDown();
                }
            }.start();
        }

        cdl.await();


        System.out.println("main finish.");
    }
}
