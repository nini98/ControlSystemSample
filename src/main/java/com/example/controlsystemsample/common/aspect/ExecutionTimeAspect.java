package com.example.controlsystemsample.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExecutionTimeAspect {
    @Around("execution(* com.example.controlsystemsample.service..*(..))")
    public Object measureExecutionTimeByPackage(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.debug("[measureExecutionTimeByPackage] Execution time of " + joinPoint.getSignature() + " : " + (endTime - startTime) + " ms");
        return proceed;
    }

    @Around("@annotation(com.example.controlsystemsample.common.annotation.MeasureExecutionTime)")
    public Object measureExecutionTimeByAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.debug("[measureExecutionTimeByAnnotation] Execution time of " + joinPoint.getSignature() + " : " + (endTime - startTime) + " ms");
        return proceed;
    }
}
