package com.rsupport.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyCategoryRequestVO {

    @JsonProperty
    private String categoryId;

    @JsonProperty
    private int categoryPriority;
}
