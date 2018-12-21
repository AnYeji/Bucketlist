package com.rsupport.bucketlist.auth.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ApiAuthenticationAspect {

  @Pointcut("execution(* com.rsupport.bucketlist.auth.controller.*.*(..))")
  public void authControllerPointCut() {

  }

  @Before("authControllerPointCut()")
  public void beforeMethod(JoinPoint joinPoint) {
  }
}
