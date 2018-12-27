package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ReturnCodes;
import com.rsupport.bucketlist.core.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostSigninResponseVO extends BaseResponseVO{

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("refresh_token")
  private String refreshToken;

  public HostSigninResponseVO(String accessToken, String refreshToken){
    super(ReturnCodes.OK);
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
