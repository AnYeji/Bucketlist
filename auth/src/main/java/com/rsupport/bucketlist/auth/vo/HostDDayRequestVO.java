package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostDDayRequestVO {

  @JsonProperty("user_id")
  private String userId;

  @JsonProperty("token")
  private String token;
}
