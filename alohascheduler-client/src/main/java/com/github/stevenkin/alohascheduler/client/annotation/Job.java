package com.github.stevenkin.alohascheduler.client.annotation;

import java.lang.annotation.*;

/**
 * 用于标识一个restful接口是一个可触发的任务
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Job {
}
