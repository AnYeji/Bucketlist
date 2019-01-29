package com.rsupport.bucketlist.core.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseVO {

  @JsonProperty("retcode")
  protected String returnCode;

  @JsonProperty("message")
  protected String message;

  public static BaseResponseVO ok() {
    return new BaseResponseVO(ApiReturnCodes.OK);
  }

  public BaseResponseVO(String returnCode) {
    this.returnCode = returnCode;
  }
}
