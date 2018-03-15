package com.yjm.study.lifecycle;

/**
 * </p>
 *
 * @author yangjinming
 * @since 18/3/15
 */
public interface ILifecycle {

    /**
     * 初始化
     */
    void init();

    /**
     * 销毁
     */
    void destory();

    /**
     * 是否存活
     * @return
     */
    boolean isAlive();
}
