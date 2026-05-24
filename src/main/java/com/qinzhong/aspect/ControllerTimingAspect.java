package com.qinzhong.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * AOP：统计 Controller 方法耗时
 * */
@Aspect
@Component
public class ControllerTimingAspect {

    private static final Logger log = LoggerFactory.getLogger(ControllerTimingAspect.class);

    @Around("execution(* com.qinzhong.controller..*(..))")
    public Object aroundController(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature().toShortString();
        long start = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            long cost = System.currentTimeMillis() - start;
            log.info("[AOP][Controller] {} 耗时 {} ms", name, cost);
        }
    }
}
