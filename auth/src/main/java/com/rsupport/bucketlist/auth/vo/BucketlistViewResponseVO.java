package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import com.rsupport.bucketlist.core.domain.Bucketlist;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BucketlistViewResponseVO extends BaseResponseVO {

  @JsonProperty
  private String title;

  @JsonProperty
  private boolean open;

  @JsonProperty
  private String category;

  @JsonProperty
  private int dDay;

  @JsonProperty
  private int userCount;

  @JsonProperty
  private int goalCount;

  @JsonProperty
  private String memo;

  @JsonProperty
  private String imgUrl1;

  @JsonProperty
  private String imgUrl2;

  @JsonProperty
  private String imgUrl3;

  public BucketlistViewResponseVO(Bucketlist bucketlist) {
    super(ApiReturnCodes.OK);
    this.title = bucketlist.getTitle();
    this.open = bucketlist.isOpen();
    this.category = bucketlist.getCategory().getName();
    this.dDay = 2;
    this.userCount = bucketlist.getUserCount();
    this.goalCount = bucketlist.getGoalCount();
    this.memo = bucketlist.getMemo();
    this.imgUrl1 = bucketlist.getImgUrl1();
    this.imgUrl2 = bucketlist.getImgUrl2();
    this.imgUrl3 = bucketlist.getImgUrl3();
  }
}
