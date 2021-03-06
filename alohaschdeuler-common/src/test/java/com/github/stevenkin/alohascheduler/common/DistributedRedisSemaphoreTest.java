package com.github.stevenkin.alohascheduler.common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class DistributedRedisSemaphoreTest {
    @Autowired
    private RedisTemplate redisTemplate;

    private ExecutorService pool = Executors.newExecutor(10, 10, 0, "test-semaphore-");

    private DistributedSemaphore semaphore;

    @Test
    public void test() throws InterruptedException, IOException {
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch1 = new CountDownLatch(10);
        semaphore = new DistributedRedisSemaphore("test", 5, redisTemplate);
        semaphore.init(null);

        for (int i = 0; i < 10; i++) {
            pool.submit(() -> {
                try {
                    String self = SystemInfo.id + "-" + Thread.currentThread().getId();
                    latch.await();
                    boolean getIt = semaphore.tryAcquire();
                    if (!getIt) {
                        System.out.println(self + "没拿到信号量");
                        latch1.countDown();
                        return;
                    }
                    System.out.println(self + "拿到信号量");
                    Thread.sleep(5000);
                    semaphore.release();
                    System.out.println(self + "释放信号量");
                    latch1.countDown();
                } catch (InterruptedException e) {

                }
            });
        }
        latch.countDown();
        latch1.await();
        semaphore.destroy();
    }
}
