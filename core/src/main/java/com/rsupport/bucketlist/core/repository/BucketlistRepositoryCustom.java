package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.Bucketlist;

import java.util.List;

public interface BucketlistRepositoryCustom {

  List<Bucketlist> getBucketlistsByUserId(String userId);

  boolean existsLessThanThreeDaysBucketlist(String userId);
}
