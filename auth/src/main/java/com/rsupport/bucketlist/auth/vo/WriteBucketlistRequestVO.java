package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseRequestVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WriteBucketlistRequestVO extends BaseRequestVO{

  @JsonProperty
  private String title;

  @JsonProperty
  private boolean open;

  @JsonProperty
  private Date dDate;

  @JsonProperty
  private int goalCount;

  @JsonProperty
  private String memo;

  @JsonProperty
  private String categoryName;

  @JsonProperty
  private String userId;

}
