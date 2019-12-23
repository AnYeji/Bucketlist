package com.rsupport.bucketlist.core.base;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
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

  public BaseResponseVO(String returnCode, String message) {
    this.returnCode = returnCode;
    this.message = message;
  }
}
