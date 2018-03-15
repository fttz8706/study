package com.yjm.study.lifecycle;

/**
 * </p>
 *
 * @author yangjinming
 * @since 18/3/15
 */
public class Main {


    public static void main(String[] args) {

        final DemoService demoService = new DemoService();

        demoService.init();

        demoService.doBiz();

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
