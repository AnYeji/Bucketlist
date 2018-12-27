package com.rsupport.bucketlist.core.exception;

public abstract class BaseException extends RuntimeException {

  public BaseException() {
    super();
  }

  public BaseException(String message) {
    super(message);
  }

  public abstract String getExceptionCode();
}
