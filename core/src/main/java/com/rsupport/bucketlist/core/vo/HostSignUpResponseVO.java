package com.rsupport.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import com.rsupport.bucketlist.core.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostSignUpResponseVO extends BaseResponseVO {

  @JsonProperty
  private String userId;

  public HostSignUpResponseVO(User user) {
    super(ApiReturnCodes.OK);
    this.userId = user.getId();
  }
}
