package com.yjm.study.compare;

import com.yjm.study.compare.comparable.Person;
import com.yjm.study.compare.comparator.Human;
import com.yjm.study.compare.comparator.HumanComparator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yangjinming on 16/5/26.
 */
public class CompareTest {

    // Comparable 接口测试
    @Test
    public void comparableTest(){
        List<Person> persons = new ArrayList<Person>(Arrays.asList(
                new Person(12, "tom"),
                new Person(10, "gim"),
                new Person(15, "alias")
        ));
        System.out.println("before sort: ");
        printPersons(persons);
        Collections.sort(persons);
        System.out.println("after sort: ");
        printPersons(persons);
    }

    // Comparator 接口测试
    @Test
    public void comparatorTest(){
        HumanComparator comparator = new HumanComparator();
        List<Human> humen = new ArrayList<Human>(Arrays.asList(
                new Human(12, "tom"),
                new Human(10, "gim"),
                new Human(15, "alias")
        ));
        System.out.println("before sort: ");
        printHumen(humen);
        Collections.sort(humen, comparator);
        System.out.println("after sort: ");
        printHumen(humen);
    }

    private void printPersons(List<Person> persons){
        for(Person item : persons){
            System.out.println(item.toString());
        }
    }

    private void printHumen(List<Human> humen){
        for(Human item : humen){
            System.out.println(item.toString());
        }
    }
}
