package com.rsupport.bucketlist.auth.controller;

import com.rsupport.bucketlist.auth.constants.ApiUriConstants;
import com.rsupport.bucketlist.auth.vo.BucketlistViewRequestVO;
import com.rsupport.bucketlist.auth.vo.BucketlistViewResponseVO;
import com.rsupport.bucketlist.auth.vo.HostHomeCompleteRequestVO;
import com.rsupport.bucketlist.auth.vo.HostHomeRequestVO;
import com.rsupport.bucketlist.auth.vo.HostHomeResponseVO;
import com.rsupport.bucketlist.auth.vo.MyPageRequestVO;
import com.rsupport.bucketlist.auth.vo.MyPageResponseVO;
import com.rsupport.bucketlist.core.exception.InvalidTokenException;
import com.rsupport.bucketlist.core.util.DateUtil;
import com.rsupport.bucketlist.core.util.JwtUtils;
import com.rsupport.bucketlist.core.util.ParameterUtil;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class HostController {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserManager userManager;

  @Autowired
  private BucketlistManager bucketlistManager;

  @GetMapping(value = ApiUriConstants.HOST_SIGNUP_CHECK)
  public HostSignupCheckResponseVO signupCheck(HostSignupCheckRequestVO requestVO) {
    ParameterUtil.checkParameter(requestVO.getUserId());

    User user = userManager.getUserById(requestVO.getUserId());
    boolean signuped = (user != null);
    return new HostSignupCheckResponseVO(signuped);
  }

  @PostMapping(value = ApiUriConstants.HOST_SIGNUP)
  public HostSignupResponseVO signup(HostSignupRequestVO requestVO) {
    ParameterUtil.checkParameter(requestVO.getEmail(), requestVO.getAccountType());

    User user = userManager.signup(requestVO);
    return new HostSignupResponseVO(user);
  }

  @GetMapping(value = ApiUriConstants.HOST_SIGNIN)
  public HostSigninResponseVO signin(HostSigninRequestVO requestVO) {
    ParameterUtil.checkParameter(requestVO.getUserId());

    User user = userManager.signin(requestVO);
    String accessToken = jwtUtils.createAccessToken(user.getEmail());
    String refreshToken = jwtUtils.createRefreshToken(accessToken);
    return new HostSigninResponseVO(accessToken, refreshToken);
  }

  @GetMapping(value = ApiUriConstants.HOST_HOME)
  public HostHomeResponseVO home(HostHomeRequestVO requestVO) {
    /*ParameterUtil.checkParameter(requestVO.getUserId(), requestVO.getToken());

    User user = userManager.getUserById(requestVO.getUserId());
    boolean isValidToken = jwtUtils.isValidAccessToken(requestVO.getToken(), user.getEmail());
    if (!isValidToken)
      throw new InvalidTokenException();

    List<Bucketlist> bucketlists = bucketlistManager.getBucketlistsByUserId(user.getId());*/
    List<Bucketlist> bucketlists = bucketlistManager.getBucketlistsByUserId("user01");
    for(Bucketlist bucketlist : bucketlists){
      bucketlist.setDDay(DateUtil.getDateDiffDay(bucketlist.getDDate(), DateUtil.getDate()));
    }
    /*boolean popupYn = bucketlistManager.existsPopupBucketlist(user.getId());*/
    boolean popupYn = false;
    return new HostHomeResponseVO(bucketlists, popupYn);
  }

  @PostMapping(value = ApiUriConstants.HOST_HOME_COMPLETE)
  public BaseResponseVO complete(@RequestBody HostHomeCompleteRequestVO requestVO){
    Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
    bucketlist.setComplete(true);
    bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  @GetMapping(value = ApiUriConstants.HOST_BUCKETLIST_CRUD)
  public BucketlistViewResponseVO getBucketlist(BucketlistViewRequestVO requestVO) {
    ParameterUtil.checkParameter(requestVO.getBucketlistId());

    Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
    return new BucketlistViewResponseVO(bucketlist);
  }

  @PostMapping(value = ApiUriConstants.HOST_BUCKETLIST_CRUD)
  public BaseResponseVO saveBucketlist(Bucketlist bucketlist) {
    ParameterUtil.checkParameter(bucketlist);

    bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  @DeleteMapping(value = ApiUriConstants.HOST_BUCKETLIST_CRUD)
  public BaseResponseVO deleteBucketlist(@PathVariable String bucketlistId) {
    ParameterUtil.checkParameter(bucketlistId);

    bucketlistManager.deleteBucketlist(bucketlistId);
    return BaseResponseVO.ok();
  }

  @GetMapping(value = ApiUriConstants.HOST_MYPAGE)
  public MyPageResponseVO mypage(MyPageRequestVO requstVO) {
    ParameterUtil.checkParameter(requstVO.getUserId());

    User user = userManager.getUserById(requstVO.getUserId());
    return new MyPageResponseVO(user);
  }
}
