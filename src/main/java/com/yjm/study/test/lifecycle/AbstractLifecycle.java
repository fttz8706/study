package com.yjm.study.test.lifecycle;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * </p>
 *
 * @author test
 * @since 18/3/15
 */
public abstract class AbstractLifecycle implements ILifecycle {

    protected volatile AtomicBoolean alive = new AtomicBoolean(false);

    @Override
    public void init() {
        if (alive.get()) {
            return;
        }
        alive.compareAndSet(false, true);
        doInit();
    }

    @Override
    public void destory() {
        if (!alive.get()) {
            return;
        }
        alive.compareAndSet(true, false);
        doDestory();
    }

    @Override
    public boolean isAlive() {
        return alive.get();
    }

    /**
     * 执行初始化动作
     */
    protected abstract void doInit();

    /**
     * 执行销毁动作
     */
    protected abstract void doDestory();
}
