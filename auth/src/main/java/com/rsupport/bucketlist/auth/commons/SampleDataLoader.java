package com.rsupport.bucketlist.auth.commons;

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
    user.setNickName("user01");
    userRepository.save(user);

    Category category1 = new Category();
    category1.setName("운동");
    category1.setUser(user);
    categoryRepository.save(category1);

    Category category2 = new Category();
    category2.setName("먹방");
    category2.setUser(user);
    categoryRepository.save(category2);

    Bucketlist bucketlist1 = new Bucketlist();
    bucketlist1.setId("bucketlist01");
    bucketlist1.setTitle("올림픽공원에서 스케이트 타기");
    bucketlist1.setDDate(DateUtil.addDays(DateUtil.getDate(), 3));
    bucketlist1.setComplete(false);
    bucketlist1.setCategory(category1);
    bucketlist1.setUser(user);
    bucketlistRepository.save(bucketlist1);

    Bucketlist bucketlist2 = new Bucketlist();
    bucketlist2.setId("bucketlist02");
    bucketlist2.setTitle("신전떡볶이 매운맛 먹기");
    bucketlist2.setPin(true);
    bucketlist2.setUserCount(2);
    bucketlist2.setGoalCount(5);
    bucketlist2.setComplete(false);
    bucketlist2.setCategory(category2);
    bucketlist2.setUser(user);
    bucketlistRepository.save(bucketlist2);
  }
}
