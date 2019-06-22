package com.yjm.study.test.extend;

/**
 * Created by test on 16/5/26.
 */
public class Son extends Father {

    private String school;

    // 如果父类重载了构造函数,则它必须提供一个无参构造函数,否则不能通过编译
    public Son(){

    }

    public Son(int age, String name, String school){
        super(age, name);
        this.school = school;
    }
}
