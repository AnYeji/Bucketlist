package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.repository.BucketlistRepository;
import com.rsupport.bucketlist.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BucketlistManagerImpl implements BucketlistManager {

  @Autowired
  private BucketlistRepository bucketlistRepository;

  @Override
  public List<Bucketlist> getBucketlistsByUserId(String userId) {
    List<Bucketlist> bucketlists = bucketlistRepository.getBucketlistsByUserId(userId);
    for(Bucketlist bucketlist : bucketlists){
      if(bucketlist.getDDate() != null)
        bucketlist.setDDay(DateUtil.getDateDiffDay(bucketlist.getDDate(), DateUtil.getDate()));
    }

    return bucketlists;
  }

  @Override
  public boolean existsPopupBucketlist(String userId, List<Integer> popupPeriodList) {
    boolean exists = false;
    for(int popupPeriod : popupPeriodList) {
      exists = exists | bucketlistRepository.existsPopupBucketlist(userId, popupPeriod);
    }
    return exists;
  }

  @Override
  public List<Bucketlist> getDDayBucketlists(String userId) {
    List<Bucketlist> bucketlists = bucketlistRepository.getDDayBucketlists(userId);

    for(Bucketlist bucketlist : bucketlists){
      bucketlist.setDDay(DateUtil.getDateDiffDay(bucketlist.getDDate(), DateUtil.getDate()));
    }

    return bucketlists;
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

  @Override
  public String getLastBucketlistId() {
    return bucketlistRepository.getLastBucketlistId();
  }

}
