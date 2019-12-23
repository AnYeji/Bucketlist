package com.rsupport.bucketlist.core.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeRequestVO {

  private String userId;

  private String filter;

  private String sort;

}
