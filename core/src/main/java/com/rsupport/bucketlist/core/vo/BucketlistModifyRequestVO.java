package com.rsupport.bucketlist.core.vo;

import java.util.Date;
import java.util.List;

import com.rsupport.bucketlist.core.base.BaseRequestVO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BucketlistModifyRequestVO extends BaseRequestVO {

  private String id;

  private String title;

  private boolean open;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dDate;

  private int goalCount;

  private String memo;

  private String categoryId;

  private String userId;

  private boolean removeImg1;

  private MultipartFile image1;

  private boolean removeImg2;

  private MultipartFile image2;

  private boolean removeImg3;

  private MultipartFile image3;

}
