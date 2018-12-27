package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.repository.UserRepository;
import com.rsupport.bucketlist.core.util.DateUtil;
import com.rsupport.bucketlist.core.vo.HostSigninRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignupRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagerImpl implements UserManager {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User getUserById(String userId) {
    return userRepository.getOne(userId);
  }

  @Override
  @Transactional
  public User signup(HostSignupRequestVO requestVO) {
    User user = new User();
    user.setEmail(requestVO.getEmail());
    user.setAccountType(requestVO.getAccountType());
    return userRepository.save(user);
  }

  @Override
  @Transactional
  public User signin(HostSigninRequestVO requestVO) {
    User user = userRepository.getOne(requestVO.getUserId());
    user.setLoginedDate(DateUtil.getDate());
    return userRepository.save(user);
  }
}
