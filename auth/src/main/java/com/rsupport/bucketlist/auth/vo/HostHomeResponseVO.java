package com.rsupport.bucketlist.auth.vo;

import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import com.rsupport.bucketlist.core.domain.Bucketlist;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HostHomeResponseVO extends BaseResponseVO {

  private List<Bucketlist> bucketlists;

  private boolean popupYn;

  public HostHomeResponseVO(String returnCode) {
    super(returnCode);
  }

  public HostHomeResponseVO(List<Bucketlist> bucketlists, boolean popupYn) {
    super(ApiReturnCodes.OK);
    this.bucketlists = bucketlists;
    this.popupYn = popupYn;
  }

}
