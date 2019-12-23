package com.rsupport.bucketlist.auth.aop;

import com.rsupport.bucketlist.core.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class ApiAuthenticationAspect {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private HttpServletRequest request;

  @Pointcut("@annotation(com.rsupport.bucketlist.auth.annotation.AccessTokenCheck)")
  public void accessTokenCheck() {}


  @Before("accessTokenCheck()")
  public void beforeMethod(JoinPoint joinPoint){
    String accessToken = request.getHeader("X-Auth-Token");
    jwtUtils.isValidAccessToken(accessToken, getUserId(joinPoint));
  }

  private String getUserId(JoinPoint joinPoint) {
    String userId = request.getParameter("userId");
    if(StringUtils.isBlank(userId)) {
      Object[] args = joinPoint.getArgs();
      for (Object arg : args) {
        if (arg instanceof HttpServletRequest) {
          userId = ((HttpServletRequest)arg).getParameter("userId");
          break;
        }
      }
    }

    return userId;
  }
}
