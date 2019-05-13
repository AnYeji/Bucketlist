package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostSignupCheckResponseVO {

  @JsonProperty
  private boolean signuped;

  public HostSignupCheckResponseVO(boolean signuped) {
    this.signuped = signuped;
  }
}
