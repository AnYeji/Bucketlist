package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BucketlistViewRequestVO {

  @JsonProperty("bucketlist_id")
  private String bucketlistId;
}
