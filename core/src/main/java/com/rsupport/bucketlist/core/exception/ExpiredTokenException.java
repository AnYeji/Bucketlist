package com.rsupport.bucketlist.core.exception;

import com.rsupport.bucketlist.core.constants.ApiReturnCodes;

public class ExpiredTokenException extends BaseException {

  public ExpiredTokenException() {
    super();
  }

  public ExpiredTokenException(String message) {
    super(message);
  }

  @Override
  public String getExceptionCode() {
    return ApiReturnCodes.ERROR_EXPIRED_TOKEN;
  }
}
