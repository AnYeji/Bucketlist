package com.rsupport.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestVO {

  @JsonProperty("email")
  private String email;

  @JsonProperty("account_type")
  private int accountType;

}
