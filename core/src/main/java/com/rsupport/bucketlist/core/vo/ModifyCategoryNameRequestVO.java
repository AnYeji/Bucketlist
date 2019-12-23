package com.rsupport.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseRequestVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyCategoryNameRequestVO extends BaseRequestVO {

  @JsonProperty
  private String userId;

  @JsonProperty
  private String id;

  @JsonProperty
  private String name;
}
