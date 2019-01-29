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

  @JsonProperty("title")
  private String title;

  @JsonProperty("open")
  private boolean open;

  @JsonProperty("category")
  private String category;

  @JsonProperty("d_day")
  private int dDay;

  @JsonProperty("user_count")
  private int userCount;

  @JsonProperty("goal_count")
  private int goalCount;

  @JsonProperty("memo")
  private String memo;

  @JsonProperty("img_url_1")
  private String imgUrl1;

  @JsonProperty("img_url_2")
  private String imgUrl2;

  @JsonProperty("img_url_3")
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
