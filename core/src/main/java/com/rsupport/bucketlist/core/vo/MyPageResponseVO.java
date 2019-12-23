package com.rsupport.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MyPageResponseVO extends BaseResponseVO {

  private String name;

  private String imageUrl;

  private int startedCount;

  private int completedCount;

  private int dDayCount;

  private List<CategoryVO> categoryList;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CategoryVO {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private int count;
  }

}
