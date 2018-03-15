package com.yjm.study.lifecycle;

/**
 * </p>
 *
 * @author yangjinming
 * @since 18/3/15
 */
public class Main {


    public static void main(String[] args) {

        DemoService demoService = new DemoService();

        demoService.init();

        System.out.println("main do something...");

        Runtime.getRuntime().addShutdownHook( new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        demoService.destory();
                    }
                })
        );
    }
}
