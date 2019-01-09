package com.rsupport.bucketlist.core.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  public static Date getDate() {
    return new Date();
  }

  public static Date addDays(Date date, int amount) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.DAY_OF_MONTH, amount);
    return c.getTime();
  }
}
