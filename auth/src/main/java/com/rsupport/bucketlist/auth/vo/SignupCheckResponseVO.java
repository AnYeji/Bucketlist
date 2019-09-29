package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupCheckResponseVO {

  @JsonProperty
  private boolean signuped;

  public SignupCheckResponseVO(boolean signuped) {
    this.signuped = signuped;
  }
}
