package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseRequestVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequestVO extends BaseRequestVO {

    @JsonProperty
    private String userId;

    @JsonProperty
    private String name;
}
