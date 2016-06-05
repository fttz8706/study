package com.yjm.study.singleton;

import java.io.*;

/**
 * Created by yangjinming on 16/6/5.
 */
public class Test {

    public static void main(String[] a){
        Singleton s1 = Singleton.getInstance();
        s1.setName("tom");
        s1.setAge(19);
        s1.setSchool(1);
        System.out.println(s1.toString());
        Test test = new Test();
        test.serialize(s1);
        Singleton s2 = test.deSerialize();
        System.out.println(s2.toString());
        System.out.println(s1 == s2); // Singleton类添加了readResolve()方法之后,结果为ture,否则是false
    }

    public void serialize(Singleton singleton){
        ObjectOutputStream oos;
        try{
            oos = new ObjectOutputStream(new FileOutputStream(new File("/Users/yangjinming/t.txt")));
            oos.writeObject(singleton);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Singleton deSerialize(){
        ObjectInputStream ois;
        Singleton singleton = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(new File("/Users/yangjinming/t.txt")));
            singleton = (Singleton)ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return singleton;
    }
}
