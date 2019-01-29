package com.rsupport.bucketlist.auth.commons;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.repository.BucketlistRepository;
import com.rsupport.bucketlist.core.repository.CategoryRepository;
import com.rsupport.bucketlist.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    User user = new User();
    user.setId("user01");
    user.setNickName("아여니");
    userRepository.save(user);

    Category category = new Category();
    category.setName("운동");
    category.setUser(user);
    categoryRepository.save(category);

    Bucketlist bucketlist = new Bucketlist();
    bucketlist.setTitle("올림픽공원에서 스케이트 타기");
    bucketlist.setCount(10);
    bucketlist.setDDate(new Date());
    bucketlist.setComplete(true);
    bucketlist.setCreatedDate(new Date());
    bucketlist.setUpdatedDate(new Date());
    bucketlist.setCategory(category);
    bucketlist.setUser(user);
    bucketlistRepository.save(bucketlist);
  }
}
