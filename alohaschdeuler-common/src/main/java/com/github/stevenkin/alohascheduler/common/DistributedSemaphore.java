package com.github.stevenkin.alohascheduler.common;

public interface DistributedSemaphore extends Lifecycle {
    /**
     * 获取信号量的id
     * @return
     */
    String getId();

    /**
     * 尝试获取一个信号量
     *
     * @return true 获取成功, false 获取失败
     */
    boolean tryAcquire();

    /**
     * 释放自己持有的信号量
     */
    void release();
}
