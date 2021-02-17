package com.github.stevenkin.alohascheduler.client.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AlohaSchedulerAspect {
    @Pointcut("@annotation(com.github.stevenkin.alohascheduler.client.annotation.Job)")
    public void annotationPointCut(){}

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return null;
    }
}
