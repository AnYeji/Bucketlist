package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.repository.UserRepository;
import com.rsupport.bucketlist.core.util.JwtUtil;
import com.rsupport.bucketlist.core.vo.HostSignupRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagerImpl implements UserManager {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public User signup(HostSignupRequestVO requestVO) {
    String token = jwtUtil.createAccessToken(requestVO.getUserId());

    User user = new User();
    user.setEmail(requestVO.getUserId());
    user.setToken(token);
    return userRepository.save(user);
  }

  @Override
  public User getUserByToken(String token) {
    return userRepository.getUserByToken(token);
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }
}
