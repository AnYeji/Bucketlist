package com.rsupport.bucketlist.core.exception;

import com.rsupport.bucketlist.core.constants.ApiReturnCodes;

public class InvalidTokenException extends BaseException {

  public InvalidTokenException() {
    super();
  }

  public InvalidTokenException(String message) {
    super(message);
  }

  @Override
  public String getExceptionCode() {
    return ApiReturnCodes.ERROR_INVALID_TOKEN;
  }
}
