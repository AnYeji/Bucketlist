package com.rsupport.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RemoveCategoryRequestVO {

    @JsonProperty
    private String userId;

    @JsonProperty
    List<String> categoryIdList = new ArrayList<>();

}
