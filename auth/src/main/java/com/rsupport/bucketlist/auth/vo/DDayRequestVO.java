package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DDayRequestVO {

  @JsonProperty
  private String userId;

  /*@JsonProperty("token")
  private String token;*/
}
