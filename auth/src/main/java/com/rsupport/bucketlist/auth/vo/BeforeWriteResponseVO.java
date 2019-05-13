package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import com.rsupport.bucketlist.core.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BeforeWriteResponseVO extends BaseResponseVO {

  @JsonProperty
  private List<Category> categoryList;

  public BeforeWriteResponseVO(String returnCode) {
    super(returnCode);
  }

  public BeforeWriteResponseVO(List<Category> categoryList) {
      super(ApiReturnCodes.OK);
      this.categoryList = categoryList;
  }
}
