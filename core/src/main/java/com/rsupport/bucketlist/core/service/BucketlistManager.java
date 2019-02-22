package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Bucketlist;

import java.util.List;

public interface BucketlistManager {

  List<Bucketlist> getBucketlistsByUserId(String userId);

  boolean existsPopupBucketlist(String userId, List<Integer> popupPeriodList);

  List<Bucketlist> getDDayBucketlists(String userId);

  Bucketlist getBucketlistById(String bucketlistId);

  Bucketlist saveBucketlist(Bucketlist bucketlist);

  void deleteBucketlist(String bucketlistId);

}
