package com.rsupport.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import com.rsupport.bucketlist.core.domain.Bucketlist;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HostDDayResponseVO extends BaseResponseVO {

    @JsonProperty
    private List<Bucketlist> bucketlists;

    public HostDDayResponseVO(String returnCode) {
        super(returnCode);
    }

    public HostDDayResponseVO(List<Bucketlist> bucketlists) {
        super(ApiReturnCodes.OK);
        this.bucketlists = bucketlists;
    }
}
