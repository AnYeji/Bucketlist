package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostSignInResponseVO extends BaseResponseVO {

  @JsonProperty
  private String accessToken;

  @JsonProperty
  private String refreshToken;

  public HostSignInResponseVO(String accessToken, String refreshToken) {
    super(ApiReturnCodes.OK);
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
