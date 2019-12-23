package com.rsupport.bucketlist.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  public static Date getDate() {
    return new Date();
  }

  public static Date getToday() {
    Date today = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    try {
      today = df.parse(getDateString("yyyy-MM-dd"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return today;
  }

  public static String getDateString(String format) {
    return getDateString(new Date(), format);
  }

  public static String getDateString(Date date, String format) {
    SimpleDateFormat df = new SimpleDateFormat(format);
    return df.format(date);
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
