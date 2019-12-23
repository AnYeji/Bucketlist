package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HostSignUpCheckResponseVO {

  @JsonProperty
  private boolean signUp;

  @JsonProperty
  private String userId;

  public HostSignUpCheckResponseVO(boolean signUp, User user) {
    this.signUp = signUp;

    if(user != null)
      this.userId = user.getId();
  }
}
