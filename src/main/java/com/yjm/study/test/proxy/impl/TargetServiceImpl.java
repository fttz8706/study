package com.yjm.study.test.proxy.impl;

import com.yjm.study.test.proxy.TargetService;

/**
 * Created by test on 16/5/25.
 */
public class TargetServiceImpl implements TargetService {

    public void doInvoke(){
        System.out.println("do invoke method in class TargetServiceImpl.");
    }
}
