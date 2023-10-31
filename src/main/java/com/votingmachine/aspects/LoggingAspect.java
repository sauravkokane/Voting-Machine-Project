package com.votingmachine.aspects;

import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
//    @After("execution(* com.votingmachine.service.Implementations.*.*(..))")
//    public void logBeforeMethod(JoinPoint joinPoint) {
//        System.out.println("Logging before " + joinPoint.getSignature().getName());
//    }
}
