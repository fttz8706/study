package com.yjm.study.compare.comparable;



/**
 * 通过比较年龄来确定两个人的大小
 * 使用实现comparable接口的方法
 * comparable是内部比较器,会入侵到要实现比较功能的类里,这个类要实现comparable的comapreTo方法
 * Created by yangjinming on 16/5/26.
 */
public class Person implements Comparable<Person>{

    private int age;
    private String name;

    public Person(){}

    public Person(int age, String name){
        this.age = age;
        this.name = name;
    }

    public int compareTo(Person o){
        if(this.age > o.getAge()){
            return 1;
        }else if (this.age < o.getAge()){
            return -1;
        }else {
            return 0;
        }
    }

    public String toString(){
        return new StringBuilder(name + ", age=" + age + ".").toString();
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
