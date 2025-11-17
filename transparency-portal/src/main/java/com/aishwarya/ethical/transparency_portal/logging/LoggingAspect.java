package com.aishwarya.ethical.transparency_portal.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.aishwarya..service..*(..)) || execution(* com.aishwarya..controller..*(..)) || execution(* com.aishwarya..repository..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.info("Entering: {}.{}()", className, methodName);

        try {
            Object result = joinPoint.proceed();
            log.info("Exiting: {}.{}()", className, methodName);
            return result;

        } catch (Exception ex) {
            log.error("Exception in {}.{}(): {}", className, methodName, ex.getMessage());
            throw ex; // Let GlobalExceptionHandler handle it
        }
    }
}
