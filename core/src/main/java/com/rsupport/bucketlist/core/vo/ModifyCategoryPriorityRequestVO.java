package com.rsupport.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ModifyCategoryPriorityRequestVO {

  @JsonProperty
  private String userId;

  @JsonProperty
  private List<String> categoryIdList = new ArrayList<>();

}
