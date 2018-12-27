package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.vo.HostSigninRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignupRequestVO;

public interface UserManager {

  User getUserByUserId(String userId);

  User signup(HostSignupRequestVO requestVO);

  User getUserByToken(String token);

  User saveUser(User user);

  User signin(HostSigninRequestVO requestVO);
}
