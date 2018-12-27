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
  public User getUserByUserId(String userId) {
    return userRepository.getOne(userId);
  }

  @Override
  @Transactional
  public User signup(HostSignupRequestVO requestVO) {
    User user = new User();
    user.setEmail(requestVO.getEmail());
    return userRepository.save(user);
  }

  @Override
  public User getUserByToken(String token) {
    return userRepository.getUserByToken(token);
  }

  @Override
  @Transactional
  public User saveUser(User user) {
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
