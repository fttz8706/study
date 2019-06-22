package com.yjm.study.test.compare.comparator;

/**
 * 待比较的类
 * Created by test on 16/5/26.
 */
public class Human {

    private int age;
    private String name;

    public Human(){}

    public Human(int age, String name){
        this.age = age;
        this.name = name;
    }

    public String toString(){
        return name + ", age=" + age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
