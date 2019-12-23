package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PinBucketlistRequestVO {

  @JsonProperty
  private String userId;

  @JsonProperty
  private String bucketlistId;
}
