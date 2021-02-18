package com.github.stevenkin.alohascheduler.common;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Executors {
    private Executors(){}

    public static ExecutorService newExecutor(Integer threadNum, Integer queueSize, final String threadNamePrefix){
        return newExecutor(threadNum, threadNum + 1, queueSize, threadNamePrefix);
    }

    public static ExecutorService newExecutor(Integer coreSize, Integer maxSize, Integer queueSize, final String threadNamePrefix){
        return new ThreadPoolExecutor(coreSize, maxSize,
                60L, TimeUnit.MILLISECONDS,
                queueSize <= 0 ? new SynchronousQueue<>() : new ArrayBlockingQueue<Runnable>(queueSize),
                new ThreadFactory() {

                    private AtomicInteger id = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName(threadNamePrefix + id.incrementAndGet());
                        return t;
                    }
                },
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }
}
