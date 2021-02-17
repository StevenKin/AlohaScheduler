package com.github.stevenkin.alohascheduler.client;

import com.github.stevenkin.alohascheduler.client.configuration.AlohaSchedulerClientConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(AlohaSchedulerClientConfiguration.class)
public @interface EnableAlohaScheduler {
}
