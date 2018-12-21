package com.rsupport.bucketlist.auth.commons;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.repository.BucketlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataLoader implements ApplicationRunner {

  @Autowired
  private BucketlistRepository bucketlistRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    createSampleData();
  }

  private void createSampleData() {
    Bucketlist bucketlist = new Bucketlist();
    bucketlistRepository.save(bucketlist);
  }
}
