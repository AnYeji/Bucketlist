package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseRequestVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BucketlistViewRequestVO extends BaseRequestVO {

  @JsonProperty
  private String bucketlistId;
}
