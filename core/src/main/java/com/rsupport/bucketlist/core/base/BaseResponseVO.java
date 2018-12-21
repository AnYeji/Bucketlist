package com.rsupport.bucketlist.core.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.constants.ReturnCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseVO {

  @JsonProperty("retcode")
  protected String returnCode;

  @JsonProperty("message")
  protected String message;

  public static BaseResponseVO ok() {
    return new BaseResponseVO(ReturnCodes.OK);
  }

  public BaseResponseVO(String returnCode) {
    this.returnCode = returnCode;
  }
}
