package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Bucketlist;

import java.util.List;

public interface BucketlistManager {

  List<Bucketlist> getBucketlistsByUserId(String userId);

  Bucketlist saveBucketlist(Bucketlist bucketlist);

  void deleteBucketlist(String bucketlistId);
}
