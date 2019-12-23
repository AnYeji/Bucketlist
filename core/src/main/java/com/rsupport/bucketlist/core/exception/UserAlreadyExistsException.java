package com.rsupport.bucketlist.core.exception;

import com.rsupport.bucketlist.core.constants.ApiReturnCodes;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getExceptionCode() {
        return ApiReturnCodes.ERROR_USER_ALREADY_EXISTS;
    }
}
