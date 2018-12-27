package com.rsupport.bucketlist.core.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtil {

  public static Date getDate() {
    return new Date();
  }
}
