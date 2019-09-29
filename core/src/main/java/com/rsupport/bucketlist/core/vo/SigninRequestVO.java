package com.rsupport.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninRequestVO {

  @JsonProperty("user_id")
  private String userId;

}
