package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.vo.SigninRequestVO;
import com.rsupport.bucketlist.core.vo.SignupRequestVO;

public interface UserManager {

  User getUserById(String userId);

  User signup(SignupRequestVO requestVO);

  User signin(SigninRequestVO requestVO);

  void deleteById(String userId);
}
