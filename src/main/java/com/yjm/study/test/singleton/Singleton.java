package com.yjm.study.test.singleton;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 单例 + 序列化
 *
 * Created by test on 16/6/5.
 */
public class Singleton implements Serializable {

    private static final long serialVersionUID = -8304078327643703462L;
    private static Singleton instance;
    private int age;
    private transient int school;
    private String name;

    private Singleton(){}

    // 双检索模式实现单例
    public static Singleton getInstance(){
        if(instance == null){
            synchronized(Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // 解决序列化后产生新对象对单例模式的破坏
    private Object readResolve(){
        return instance;
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException{
        ois.defaultReadObject();
        ois.readInt();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException{
        oos.defaultWriteObject();
        oos.writeInt(age);
    }

    public String toString(){
        return new StringBuilder("name:" + name + ", age:" + age).toString();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
