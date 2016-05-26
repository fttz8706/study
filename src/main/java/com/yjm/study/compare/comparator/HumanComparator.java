package com.yjm.study.compare.comparator;

import java.util.Comparator;

/**
 * 对Human类进行比较的比较器
 * Created by yangjinming on 16/5/26.
 */
public class HumanComparator implements Comparator<Human> {

    public int compare(Human h1, Human h2){
        if(h1.getAge() > h2.getAge()){
            return 1;
        }else if(h1.getAge() < h2.getAge()){
            return -1;
        }else {
            return 0;
        }
    }
}
