package com.yjm.study.unsafe;

public class Person {

    private String name;
    private int age;
    private static final String s = "dd";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
