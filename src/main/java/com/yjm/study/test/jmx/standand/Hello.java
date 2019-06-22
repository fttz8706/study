package com.yjm.study.test.jmx.standand;

public class Hello implements HelloMBean{

    private String name;

    public void setName(String name) {
        System.out.println("set name:" + name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
