package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.repository.BucketlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BucketlistManagerImpl implements BucketlistManager {

  @Autowired
  private BucketlistRepository bucketlistRepository;

  @Override
  public List<Bucketlist> getBucketlistsByUserId(String userId) {
    return bucketlistRepository.getBucketlistsByUserId(userId);
  }

  @Override
  public Bucketlist getBucketlistById(String bucketlistId) {
    return bucketlistRepository.getOne(bucketlistId);
  }

  @Override
  public Bucketlist saveBucketlist(Bucketlist bucketlist) {
    return bucketlistRepository.save(bucketlist);
  }

  @Override
  public void deleteBucketlist(String bucketlistId) {

  }

}
