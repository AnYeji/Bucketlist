package com.rsupport.bucketlist.core.repository;

import java.util.Date;

public interface HistoryRevisionRepository {

  long getPreviousRevisionNumber(Class<?> clazz, String id);

  long getNextRevisionNumber();

  Date getRevisionDate();

  void getHistoryListWithJoinField(Class<?> clazz);
}
