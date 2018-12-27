package com.rsupport.bucketlist.core.exception;

public class ParameterException extends BaseException {

  public ParameterException() {
    super();
  }

  public ParameterException(String message) {
    super(message);
  }

  @Override
  public String getExceptionCode() {
    return null;
  }
}
