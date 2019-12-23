package com.rsupport.bucketlist.core.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateProfileRequestVO {

  private String userId;

  private String name;

  private boolean defaultImg;

  private MultipartFile multipartFile;

}
