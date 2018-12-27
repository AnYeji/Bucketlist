package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.vo.HostSigninRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignupRequestVO;

public interface UserManager {

  User getUserById(String userId);

  User signup(HostSignupRequestVO requestVO);

  User signin(HostSigninRequestVO requestVO);
}
