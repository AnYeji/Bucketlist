package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.vo.HostSignupRequestVO;

public interface UserManager {

  User signup(HostSignupRequestVO requestVO);

  User getUserByToken(String token);

  User saveUser(User user);
}
