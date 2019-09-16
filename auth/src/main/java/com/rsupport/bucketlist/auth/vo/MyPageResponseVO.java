package com.rsupport.bucketlist.auth.vo;

import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.domain.User;
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

  private Map<String, Integer> categoryMap = new HashMap<>();

}
