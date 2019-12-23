package com.rsupport.bucketlist.core.base;

import com.rsupport.bucketlist.core.exception.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerAdvice {

  @ResponseBody
  @ExceptionHandler(BaseException.class)
  public BaseResponseVO baseException(BaseException ex) {
    String returnCode = ex.getExceptionCode();
    String message = ex.getMessage();
    return new BaseResponseVO(returnCode, message);
  }
}
