package com.yjm.study.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;
import com.yjm.study.jmx.standand.Hello;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 标准MBean
 * 这种类型的MBean最简单，它能管理的资源（包括属性，方法，时间）必须定义在接口中，
 * 然后MBean必须实现这个接口。它的命名也必须遵循一定的规范，例如我们的MBean为Hello，
 * 则接口必须为HelloMBean。
 */
public class StandandMBeanAgent {

    public static void main(String[] a) throws Exception {
//        standandMBean();
        htmlAdaptorServer();
    }

    /**
     * 启动之后可以通过jconsole访问
     * @throws Exception
     */
    private static void standandMBean() throws Exception {
        final MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        final ObjectName objectName = new ObjectName("com.yjm.study.jmx:name=hello world");
        mBeanServer.registerMBean(new Hello(), objectName);

        Runtime.getRuntime().addShutdownHook(
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            mBeanServer.unregisterMBean(objectName);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                }
        );
        System.in.read();
    }


    /**
     * 启动之后，可以通过浏览器访问，localhost:8082
     * @throws Exception
     */
    private static void htmlAdaptorServer() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("com.yjm.study.jmx:name=hello bean");
        mBeanServer.registerMBean(new Hello(), helloName);

        ObjectName adapterName = new ObjectName("com.yjm.study.jmx:name=adapter bean, port=8999");
        final HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
        mBeanServer.registerMBean(adaptor, adapterName);
        adaptor.start();
        Runtime.getRuntime().addShutdownHook(
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            adaptor.stop();
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                }
        );
        System.in.read();
    }

}
