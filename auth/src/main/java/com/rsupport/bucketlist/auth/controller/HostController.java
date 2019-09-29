package com.rsupport.bucketlist.auth.controller;

import com.rsupport.bucketlist.auth.constants.ApiUriConstants;
import com.rsupport.bucketlist.auth.vo.RemoveAccountRequestVO;
import com.rsupport.bucketlist.auth.vo.UpdateProfileRequestVO;
import com.rsupport.bucketlist.core.vo.ModifyCategoryRequestVO;
import com.rsupport.bucketlist.core.vo.RemoveCategoryRequestVO;
import com.rsupport.bucketlist.core.constants.CommonCodes;
import com.rsupport.bucketlist.auth.vo.BeforeWriteResponseVO;
import com.rsupport.bucketlist.auth.vo.BucketlistViewResponseVO;
import com.rsupport.bucketlist.auth.vo.WriteBucketlistRequestVO;
import com.rsupport.bucketlist.auth.vo.DDayRequestVO;
import com.rsupport.bucketlist.auth.vo.DDayResponseVO;
import com.rsupport.bucketlist.auth.vo.CompleteBucketlistRequestVO;
import com.rsupport.bucketlist.auth.vo.HomeRequestVO;
import com.rsupport.bucketlist.auth.vo.HomeResponseVO;
import com.rsupport.bucketlist.auth.vo.PinBucketlistRequestVO;
import com.rsupport.bucketlist.auth.vo.MyPageRequestVO;
import com.rsupport.bucketlist.auth.vo.MyPageResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.service.CategoryManager;
import com.rsupport.bucketlist.core.service.FileUploadService;
import com.rsupport.bucketlist.core.util.JwtUtils;
import com.rsupport.bucketlist.core.util.ParameterUtil;
import com.rsupport.bucketlist.core.vo.SigninRequestVO;
import com.rsupport.bucketlist.auth.vo.SigninResponseVO;
import com.rsupport.bucketlist.auth.vo.SignupCheckRequestVO;
import com.rsupport.bucketlist.auth.vo.SignupCheckResponseVO;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.vo.SignupRequestVO;
import com.rsupport.bucketlist.core.vo.SignupResponseVO;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class HostController {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserManager userManager;

  @Autowired
  private BucketlistManager bucketlistManager;

  @Autowired
  private CategoryManager categoryManager;

  @Autowired
  private FileUploadService fileUploadService;

  /*@Autowired
  private ServicePropertiesUtil servicePropertiesUtil;*/

  @GetMapping(value = ApiUriConstants.HOST_SIGNUP_CHECK)
  public SignupCheckResponseVO signupCheck(SignupCheckRequestVO requestVO) {
    ParameterUtil.checkParameter(requestVO.getUserId());

    User user = userManager.getUserById(requestVO.getUserId());
    boolean signuped = (user != null);
    return new SignupCheckResponseVO(signuped);
  }

  @PostMapping(value = ApiUriConstants.HOST_SIGNUP)
  public SignupResponseVO signup(SignupRequestVO requestVO) {
    ParameterUtil.checkParameter(requestVO.getEmail(), requestVO.getAccountType());

    User user = userManager.signup(requestVO);
    return new SignupResponseVO(user);
  }

  @GetMapping(value = ApiUriConstants.HOST_SIGNIN)
  public SigninResponseVO signin(SigninRequestVO requestVO) {
    ParameterUtil.checkParameter(requestVO.getUserId());

    User user = userManager.signin(requestVO);
    String accessToken = jwtUtils.createAccessToken(user.getEmail());
    String refreshToken = jwtUtils.createRefreshToken(accessToken);
    return new SigninResponseVO(accessToken, refreshToken);
  }

  @GetMapping(value = ApiUriConstants.HOST_HOME)
  public HomeResponseVO home(HomeRequestVO requestVO) {
    /*ParameterUtil.checkParameter(requestVO.getUserId(), requestVO.getToken());
    
    User user = userManager.getUserById(requestVO.getUserId());
    boolean isValidToken = jwtUtils.isValidAccessToken(requestVO.getToken(), user.getEmail());
    if (!isValidToken)
      throw new InvalidTokenException();*/

    List<Bucketlist> bucketlists = bucketlistManager.getBucketlistsByUserId("user01");

    String popupPeriodStr = "1,2,3,7,30";
    List<Integer> popupPeriodList = new ArrayList<>();
    for(String popupPeriod : popupPeriodStr.split(",")){
      popupPeriodList.add(Integer.parseInt(popupPeriod));
    }
    boolean popupYn = bucketlistManager.existsPopupBucketlist("user01", popupPeriodList);

    return new HomeResponseVO(bucketlists, popupYn);
  }

  @GetMapping(value = ApiUriConstants.HOST_D_DAY)
  public DDayResponseVO dDay(DDayRequestVO requestVO) {
    /*ParameterUtil.checkParameter(requestVO.getUserId(), requestVO.getToken());
    
    User user = userManager.getUserById(requestVO.getUserId());
    boolean isValidToken = jwtUtils.isValidAccessToken(requestVO.getToken(), user.getEmail());
    if (!isValidToken)
      throw new InvalidTokenException();*/

    List<Bucketlist> bucketlists = bucketlistManager.getDDayBucketlist("user01");
    return new DDayResponseVO(bucketlists);
  }

  @PostMapping(value = ApiUriConstants.HOST_BUCKETLIST_COMPLETE)
  public BaseResponseVO completeBucketlist(@RequestBody CompleteBucketlistRequestVO requestVO) {
    Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
    bucketlist.setUserCount(bucketlist.getUserCount() + 1);

    if (bucketlist.getUserCount() == bucketlist.getGoalCount())
      bucketlist.setStatus(CommonCodes.BucketlistStatus.AFTER);

    bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  @PostMapping(value = ApiUriConstants.HOST_BUCKETLIST_PIN)
  public BaseResponseVO pinBucketlist(@RequestBody PinBucketlistRequestVO requestVO) {
    Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
    if (bucketlist.isPin()) {
      bucketlist.setPin(false);
    } else {
      bucketlist.setPin(true);
    }

    bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  @GetMapping(value = ApiUriConstants.HOST_BUCKETLIST_BEFORE_WRITE)
  public BeforeWriteResponseVO beforeWrite(String userId) {
    User user = userManager.getUserById("user01");

    return new BeforeWriteResponseVO(user.getCategoryList());
  }

  @PostMapping(value = ApiUriConstants.HOST_BUCKETLIST_WRITE)
  public BaseResponseVO writeBucketlist(@RequestBody WriteBucketlistRequestVO requestVO) {
    Category category = categoryManager.getCategoryByName(requestVO.getCategoryName());

    User user = userManager.getUserById("user01");

    Bucketlist bucketlist = new Bucketlist();
    bucketlist.setTitle(requestVO.getTitle());
    bucketlist.setOpen(requestVO.isOpen());
    bucketlist.setGoalCount(requestVO.getGoalCount());
    bucketlist.setDDate(requestVO.getDDate());
    bucketlist.setMemo(requestVO.getMemo());
    bucketlist.setCategory(category);
    bucketlist.setUser(user);
    bucketlistManager.saveBucketlist(bucketlist);

    return BaseResponseVO.ok();
  }

  @PostMapping(value = ApiUriConstants.HOST_IMAGE_UPLOAD)
  public BaseResponseVO bucketlistImageUpload(HttpServletRequest request) {
    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
    log.info(multipartRequest.getFileMap()+"zzzzzzzzzzzzzzzzzzzzz");
    Iterator<String> iterator = multipartRequest.getFileNames();
    while(iterator.hasNext()) {
      log.info("image upload");
      MultipartFile file = multipartRequest.getFile(iterator.next());
      log.info(file.getOriginalFilename()+"aaaaaaaaaaaaa");
      log.info(file.getSize()+"bbbbbbbbbbbbbbbbbbb");
      fileUploadService.upload(file);
    }

    return BaseResponseVO.ok();
  }

  @GetMapping(value = ApiUriConstants.HOST_BUCKETLIST_CRUD)
  public BucketlistViewResponseVO getBucketlist(@PathVariable String id) {
    ParameterUtil.checkParameter(id);

    Bucketlist bucketlist = bucketlistManager.getBucketlistById(id);
    return new BucketlistViewResponseVO(bucketlist);
  }

  @PostMapping(value = ApiUriConstants.HOST_BUCKETLIST_CRUD)
  public BaseResponseVO modifyBucketlist(@PathVariable String id, WriteBucketlistRequestVO requestVO) {
    Category category = categoryManager.getCategoryByName(requestVO.getCategoryName());

    User user = userManager.getUserById("user01");

    Bucketlist bucketlist = bucketlistManager.getBucketlistById(id);
    bucketlist.setTitle(requestVO.getTitle());
    bucketlist.setOpen(requestVO.isOpen());
    bucketlist.setGoalCount(requestVO.getGoalCount());
    bucketlist.setDDate(requestVO.getDDate());
    bucketlist.setMemo(requestVO.getMemo());
    bucketlist.setCategory(category);
    bucketlist.setUser(user);
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
    /*ParameterUtil.checkParameter(requstVO.getUserId());*/

    User user = userManager.getUserById("user01");

    MyPageResponseVO responseVO = new MyPageResponseVO();
    responseVO.setName(user.getName());
    responseVO.setImageUrl(user.getImgUrl());

    int startedCount = bucketlistManager.getStartedBucklistCount(user.getId());
    responseVO.setStartedCount(startedCount);

    int completedCount = bucketlistManager.getCompletedBucketlistCount(user.getId());
    responseVO.setCompletedCount(completedCount);

    int dDayCount = bucketlistManager.getDDayBucketlist(user.getId()).size();
    responseVO.setDDayCount(dDayCount);

    Map<String, Integer> categoryMap = setCategoryMap(user);
    responseVO.setCategoryMap(categoryMap);

    responseVO.setReturnCode(ApiReturnCodes.OK);
    return responseVO;
  }

  private Map<String, Integer> setCategoryMap(User user) {
    Map<String, Integer> categoryMap = new HashMap<>();
    for(Category category : user.getCategoryList()) {
      int categoryCount = 0;
      for(Bucketlist bucketlist : user.getBucketlists()) {
        if(category.equals(bucketlist.getCategory())) {
          categoryCount++;
        }
      }
      categoryMap.put(category.getName(), categoryCount);
    }
    return categoryMap;
  }

  @PostMapping(value = ApiUriConstants.HOST_PROFILE)
  public BaseResponseVO updateProfile(@RequestBody UpdateProfileRequestVO requestVO) {
    User user = userManager.getUserById(requestVO.getUserId());
    user.setName(requestVO.getName());
    user.setImgUrl("");
    return BaseResponseVO.ok();
  }

  @PostMapping(value = ApiUriConstants.HOST_CATEGORY)
  public BaseResponseVO modifyCategory(@RequestBody ModifyCategoryRequestVO requestVO) {
    categoryManager.modify(requestVO);
    return BaseResponseVO.ok();
  }

  @DeleteMapping(value = ApiUriConstants.HOST_CATEGORY)
  public BaseResponseVO removeCategory(@RequestBody RemoveCategoryRequestVO requestVO) {
    categoryManager.remove(requestVO.getCategoryId());
    return BaseResponseVO.ok();
  }

  @DeleteMapping(value = ApiUriConstants.HOST_ACCOUNT)
  public BaseResponseVO removeAccount(@RequestBody RemoveAccountRequestVO requestVO) {
    userManager.deleteById(requestVO.getUserId());
    return BaseResponseVO.ok();
  }

  //개인정보처리방침, 이용약관
}
