package com.rsupport.bucketlist.core.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  public static Date getDate() {
    return new Date();
  }

  private static long getDateDiff(Date source, Date target) {
    return source.getTime() - target.getTime();
  }

  public static int getDateDiffDay(Date source, Date target) {
    int evalDay = 1000 * 60 * 60 * 24;
    long diffTime = getDateDiff(source, target);
    return (int)(diffTime / evalDay);
  }


  public static Date addDays(Date date, int amount) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.DAY_OF_MONTH, amount);
    return c.getTime();
  }
}
