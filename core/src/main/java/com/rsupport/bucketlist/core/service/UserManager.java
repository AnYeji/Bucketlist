package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.vo.CreateProfileRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignInRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignUpRequestVO;

public interface UserManager {

  User getUserById(String userId);

  User getUserByEmail(String email);

  User signup(HostSignUpRequestVO requestVO);

  User signin(HostSignInRequestVO requestVO);

  void createProfile(CreateProfileRequestVO requestVO);

  void remove(String userId);
}
