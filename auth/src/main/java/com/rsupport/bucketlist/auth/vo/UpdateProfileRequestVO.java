package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequestVO {

    @JsonProperty
    private String userId;

    @JsonProperty
    private String name;

}
