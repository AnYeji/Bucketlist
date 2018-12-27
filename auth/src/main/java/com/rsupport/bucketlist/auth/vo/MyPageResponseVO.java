package com.rsupport.bucketlist.auth.vo;

import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import com.rsupport.bucketlist.core.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageResponseVO extends BaseResponseVO {

  public MyPageResponseVO(User user) {
    super(ApiReturnCodes.OK);
  }
}
