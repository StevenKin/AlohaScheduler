package com.github.stevenkin.alohascheduler.client.configuration;

import com.github.stevenkin.alohascheduler.client.core.AlohaSchedulerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlohaSchedulerClientConfiguration {
    @Bean
    public AlohaSchedulerAspect aspect() {
        return new AlohaSchedulerAspect();
    }
}
