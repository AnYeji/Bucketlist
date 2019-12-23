package com.rsupport.bucketlist.auth.common;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.repository.BucketlistRepository;
import com.rsupport.bucketlist.core.repository.CategoryRepository;
import com.rsupport.bucketlist.core.repository.UserRepository;
import com.rsupport.bucketlist.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataLoader implements ApplicationRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private BucketlistRepository bucketlistRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    createSampleData();
  }

  private void createSampleData() {

  }
}
