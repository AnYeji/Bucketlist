package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.Bucketlist;

import java.util.List;

public interface BucketlistRepositoryCustom {

  List<Bucketlist> getBucketlistsByUserId(String userId);

  boolean existsPopupBucketlist(String userId, int popupPeriod);

  List<Bucketlist> getDDayBucketlists(String userId);
}
