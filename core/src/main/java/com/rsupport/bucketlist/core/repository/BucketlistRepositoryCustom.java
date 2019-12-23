package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.vo.HomeRequestVO;

import java.util.Date;
import java.util.List;

public interface BucketlistRepositoryCustom {

  List<Bucketlist> getBucketlists(HomeRequestVO requestVO);

  boolean existsPopupBucketlist(String userId, int popupPeriod);

  List<Bucketlist> getDDayBucketlist(String userId);

  List<Bucketlist> getBucketlistsByDDate(Date date);

  String getLastBucketlistId();

  int getStartedBucketlistCount(String userId);

  int getCompletedBucketlistCount(String userId);

  List<Bucketlist> getBucketlistByCategoryId(String categoryId);
}
