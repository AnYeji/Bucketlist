package com.rsupport.bucketlist.auth.controller;

import com.rsupport.bucketlist.auth.constants.ApiUriConstants;
import com.rsupport.bucketlist.auth.vo.HostHomeRequestVO;
import com.rsupport.bucketlist.auth.vo.HostHomeResponseVO;
import com.rsupport.bucketlist.core.util.JwtUtils;
import com.rsupport.bucketlist.core.vo.HostSigninRequestVO;
import com.rsupport.bucketlist.auth.vo.HostSigninResponseVO;
import com.rsupport.bucketlist.auth.vo.HostSignupCheckRequestVO;
import com.rsupport.bucketlist.auth.vo.HostSignupCheckResponseVO;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.vo.HostSignupRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignupResponseVO;
import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.service.BucketlistManager;
import com.rsupport.bucketlist.core.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HostController {

  @Autowired
  private UserManager userManager;

  @Autowired
  private BucketlistManager bucketlistManager;

  @Autowired
  private JwtUtils jwtUtils;

  @GetMapping(value = ApiUriConstants.HOST_SIGNUP_CHECK)
  public HostSignupCheckResponseVO signupCheck(HostSignupCheckRequestVO requestVO) {
    User user = userManager.getUserByUserId(requestVO.getUserId());
    boolean signuped = (user != null);
    return new HostSignupCheckResponseVO(signuped);
  }

  @PostMapping(value = ApiUriConstants.HOST_SIGNUP)
  public HostSignupResponseVO signup(HostSignupRequestVO requestVO) {
    User user = userManager.signup(requestVO);
    return new HostSignupResponseVO(user);
  }

  @GetMapping(value = ApiUriConstants.HOST_SIGNIN)
  public HostSigninResponseVO signin(HostSigninRequestVO requestVO) {
    User user = userManager.signin(requestVO);

    String accessToken = jwtUtils.createAccessToken(user.getEmail());
    String refreshToken = jwtUtils.createRefreshToken(accessToken);
    return new HostSigninResponseVO(accessToken, refreshToken);
  }

  @GetMapping(value = ApiUriConstants.HOST_HOME)
  public HostHomeResponseVO home(HostHomeRequestVO requestVO) {
    User user = userManager.getUserByToken(requestVO.getToken());
    List<Bucketlist> bucketlists = bucketlistManager.getBucketlistsByUserId(user.getId());
    return new HostHomeResponseVO(bucketlists);
  }

  /*@GetMapping(value = ApiUriConstants.HOST_BUCKETLIST_CRUD)
  public Bucketlist getBucketlist() {
  
  }*/

  @PostMapping(value = ApiUriConstants.HOST_BUCKETLIST_CRUD)
  public BaseResponseVO saveBucketlist(Bucketlist bucketlist) {
    bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  @DeleteMapping(value = ApiUriConstants.HOST_BUCKETLIST_CRUD)
  public BaseResponseVO deleteBucketlist(@PathVariable String bucketlistId) {
    bucketlistManager.deleteBucketlist(bucketlistId);
    return BaseResponseVO.ok();
  }

  /*@GetMapping(value = ApiUriConstants.HOST_RANKING)
  public RankingResponseVO ranking() {
  
  }
  
  @GetMapping(value = ApiUriConstants.HOST_SEARCH)
  public void search() {
  
  }
  
  @GetMapping(value = ApiUriConstants.HOST_MYPAGE)
  public MyPageResponseVO mypage() {
  
  }*/
}
