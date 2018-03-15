package com.yjm.study.lifecycle;

import java.util.concurrent.*;

/**
 * </p>
 *
 * @author yangjinming
 * @since 18/3/15
 */
public class DemoService extends AbstractLifecycle {

    private ThreadPoolExecutor threadPool;

    @Override
    protected void doInit() {
        threadPool = new ThreadPoolExecutor(
                10,
                50,
                100,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>()
        );
        System.out.println("resources inited...");
    }

    @Override
    protected void doDestory() {
        threadPool.shutdown();
        System.out.println("resources is destoried...");
    }


    public void doBiz() {
        System.out.println("doing business handler...");
    }
}
