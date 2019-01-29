package com.rsupport.bucketlist.core.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ServicePropertiesUtil {

    /*@Value("${popup.period}")*/
    private Integer popupPeriod;
}
