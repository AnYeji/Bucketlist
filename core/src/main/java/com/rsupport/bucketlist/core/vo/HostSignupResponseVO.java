package com.rsupport.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ReturnCodes;
import com.rsupport.bucketlist.core.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostSignupResponseVO extends BaseResponseVO {

  @JsonProperty("user_id")
  private String userId;

  public HostSignupResponseVO(User user) {
    super(ReturnCodes.OK);
    this.userId = userId;
  }
}
