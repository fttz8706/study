package com.yjm.study.test.lifecycle;

/**
 * </p>
 *
 * @author test
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
