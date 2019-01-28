package com.rsupport.bucketlist.auth.commons;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.repository.BucketlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    bucketlist.setTitle("올림픽공원에서 스케이트 타기");
    bucketlist.setCount(10);
    bucketlist.setCreatedDate(new Date());
    bucketlist.setUpdatedDate(new Date());

    Category category = new Category();
    bucketlist.setCategory(category);

    User user = new User();
    user.setNickName("아여니");
    bucketlist.setUser(user);
    bucketlistRepository.save(bucketlist);
  }
}
