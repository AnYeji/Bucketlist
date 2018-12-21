package com.rsupport.bucketlist.auth.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostSigninResponseVO {

  private String accessToken;

  private String refreshToken;
}
