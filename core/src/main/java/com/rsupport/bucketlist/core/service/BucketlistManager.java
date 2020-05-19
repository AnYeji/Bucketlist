package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.vo.BucketlistModifyRequestVO;
import com.rsupport.bucketlist.core.vo.BucketlistWriteRequestVO;
import com.rsupport.bucketlist.core.vo.DDayRequestVO;
import com.rsupport.bucketlist.core.vo.HomeRequestVO;

import java.util.Date;
import java.util.List;

public interface BucketlistManager {

  List<Bucketlist> getBucketlists(HomeRequestVO requestVO);

  List<Bucketlist> getBucketlistByCategoryId(String categoryId);

  boolean existsPopupBucketlist(String userId, int popupPeriod);

  List<Bucketlist> getDDayBucketlist(String userId, String filter );

  List<Bucketlist> getBucketlistsByDDate(Date date);

  Bucketlist getBucketlistById(String bucketlistId);

  void writeBucketlist(BucketlistWriteRequestVO requestVO);

  void saveBucketlist(Bucketlist bucketlist);

  void modifyBucketlist(BucketlistModifyRequestVO requestVO);

  void deleteBucketlist(String bucketlistId);

  int getStartedBucklistCount(String userId);

  int getCompletedBucketlistCount(String userId);

  String getLastBucketlistId();

}
