package com.yjm.study.proxy.impl;

import com.yjm.study.proxy.TargetService;

/**
 * Created by yangjinming on 16/5/25.
 */
public class TargetServiceImpl implements TargetService {

    public void doInvoke(){
        System.out.println("do invoke method in class TargetServiceImpl.");
    }
}
