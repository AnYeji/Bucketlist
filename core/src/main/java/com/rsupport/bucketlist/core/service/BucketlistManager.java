package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Bucketlist;

import java.util.List;

public interface BucketlistManager {

  List<Bucketlist> getBucketlistsByUserId(String userId);

  boolean existsPopupBucketlist(String userId, List<Integer> popupPeriodList);

  List<Bucketlist> getDDayBucketlist(String userId);

  Bucketlist getBucketlistById(String bucketlistId);

  Bucketlist saveBucketlist(Bucketlist bucketlist);

  void deleteBucketlist(String bucketlistId);

  int getStartedBucklistCount(String userId);

  int getCompletedBucketlistCount(String userId);

  String getLastBucketlistId();

}
