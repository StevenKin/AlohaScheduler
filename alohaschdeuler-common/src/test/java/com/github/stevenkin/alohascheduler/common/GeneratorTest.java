package com.github.stevenkin.alohascheduler.common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class GeneratorTest {
    @Autowired
    private RedisTemplate redisTemplate;

    private ExecutorService pool = Executors.newExecutor(10, 10, 0, "test-semaphore-");

    private IDGenerator idGenerator;
    private MessageSerialNoGenerator messageSerialNoGenerator;

    @Before
    public void before() {
        idGenerator = new RedisIDGenerator(redisTemplate);
        messageSerialNoGenerator = new RedisMessageSerialNoGenerator(redisTemplate);

        idGenerator.init(null);
        messageSerialNoGenerator.init(null);
    }

    @Test
    public void test() throws InterruptedException, IOException {
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch1 = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            pool.submit(() -> {
                try {
                    latch.await();
                    System.out.println(Thread.currentThread().getId() + ":" + idGenerator.get() + ":" + messageSerialNoGenerator.get());
                    latch1.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        latch.countDown();
        latch1.await();
        idGenerator.destroy();
        messageSerialNoGenerator.destroy();
    }

}
