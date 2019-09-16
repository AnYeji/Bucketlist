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
    User user = new User();
    user.setId("user01");
    user.setName("user01");
    userRepository.save(user);

    Category category1 = new Category();
    category1.setName("운동");
    category1.setPriority(1);
    category1.setUser(user);
    categoryRepository.save(category1);

    Category category2 = new Category();
    category2.setName("먹방");
    category2.setPriority(2);
    category2.setUser(user);
    categoryRepository.save(category2);

    Category category3 = new Category();
    category3.setName("쇼핑");
    category3.setPriority(3);
    category3.setUser(user);
    categoryRepository.save(category3);

    Category category4 = new Category();
    category4.setName("여행");
    category4.setPriority(4);
    category4.setUser(user);
    categoryRepository.save(category4);

    Category category5 = new Category();
    category5.setName("몰라");
    category5.setPriority(5);
    category5.setUser(user);
    categoryRepository.save(category5);

    Bucketlist bucketlist1 = new Bucketlist();
    bucketlist1.setTitle("올림픽공원에서 스케이트 타기");
    bucketlist1.setDDate(DateUtil.addDays(DateUtil.getDate(), 3));
    bucketlist1.setGoalCount(1);
    bucketlist1.setStatus("0");
    bucketlist1.setCategory(category1);
    bucketlist1.setUser(user);
    bucketlistRepository.save(bucketlist1);

    Bucketlist bucketlist2 = new Bucketlist();
    bucketlist2.setTitle("신전떡볶이 매운맛 먹기");
    bucketlist2.setPin(true);
    bucketlist2.setUserCount(2);
    bucketlist2.setGoalCount(5);
    bucketlist2.setStatus("0");
    bucketlist2.setCategory(category2);
    bucketlist2.setUser(user);
    bucketlistRepository.save(bucketlist2);

    Bucketlist bucketlist3 = new Bucketlist();
    bucketlist3.setTitle("과자파티하기");
    bucketlist3.setDDate(DateUtil.addDays(DateUtil.getDate(), 20));
    bucketlist3.setUserCount(4);
    bucketlist3.setGoalCount(4);
    bucketlist3.setStatus("0");
    bucketlist3.setCategory(category2);
    bucketlist3.setUser(user);
    bucketlistRepository.save(bucketlist3);

    Bucketlist bucketlist4 = new Bucketlist();
    bucketlist4.setTitle("삼바 배우기");
    bucketlist4.setUserCount(1);
    bucketlist4.setGoalCount(1);
    bucketlist4.setStatus("1");
    bucketlist4.setCategory(category1);
    bucketlist4.setUser(user);
    bucketlistRepository.save(bucketlist4);

    Bucketlist bucketlist5 = new Bucketlist();
    bucketlist5.setTitle("갤럭시 S10으로 바꾸기");
    bucketlist5.setUserCount(1);
    bucketlist5.setGoalCount(1);
    bucketlist5.setStatus("1");
    bucketlist5.setCategory(category3);
    bucketlist5.setUser(user);
    bucketlistRepository.save(bucketlist5);

    Bucketlist bucketlist6 = new Bucketlist();
    bucketlist6.setTitle("남자친구랑 유럽여행 가기");
    bucketlist6.setUserCount(1);
    bucketlist6.setGoalCount(1);
    bucketlist6.setStatus("1");
    bucketlist6.setCategory(category4);
    bucketlist6.setUser(user);
    bucketlistRepository.save(bucketlist6);
  }
}
