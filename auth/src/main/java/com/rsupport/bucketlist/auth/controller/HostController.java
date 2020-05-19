package com.rsupport.bucketlist.auth.controller;

import com.rsupport.bucketlist.auth.annotation.AccessTokenCheck;
import com.rsupport.bucketlist.auth.constants.ApiUriConstants;
import com.rsupport.bucketlist.auth.vo.BucketlistRemoveRequestVO;
import com.rsupport.bucketlist.auth.vo.CancelBucketlistRequestVO;
import com.rsupport.bucketlist.auth.vo.CreateCategoryRequestVO;
import com.rsupport.bucketlist.core.vo.ModifyCategoryNameRequestVO;
import com.rsupport.bucketlist.core.vo.BucketlistModifyRequestVO;
import com.rsupport.bucketlist.auth.vo.CategoryRequestVO;
import com.rsupport.bucketlist.auth.vo.CategoryResponseVO;
import com.rsupport.bucketlist.auth.vo.WithdrawalRequestVO;
import com.rsupport.bucketlist.core.service.UserManager;
import com.rsupport.bucketlist.core.vo.CreateProfileRequestVO;
import com.rsupport.bucketlist.core.vo.ModifyCategoryPriorityRequestVO;
import com.rsupport.bucketlist.core.vo.RemoveCategoryRequestVO;
import com.rsupport.bucketlist.core.constants.CommonCodes;
import com.rsupport.bucketlist.auth.vo.BeforeWriteResponseVO;
import com.rsupport.bucketlist.auth.vo.BucketlistViewResponseVO;
import com.rsupport.bucketlist.core.vo.BucketlistWriteRequestVO;
import com.rsupport.bucketlist.core.vo.DDayRequestVO;
import com.rsupport.bucketlist.auth.vo.DDayResponseVO;
import com.rsupport.bucketlist.auth.vo.CompleteBucketlistRequestVO;
import com.rsupport.bucketlist.core.vo.HomeRequestVO;
import com.rsupport.bucketlist.auth.vo.HomeResponseVO;
import com.rsupport.bucketlist.auth.vo.PinBucketlistRequestVO;
import com.rsupport.bucketlist.auth.vo.MyPageRequestVO;
import com.rsupport.bucketlist.core.vo.MyPageResponseVO;
import com.rsupport.bucketlist.core.constants.ApiReturnCodes;
import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.service.CategoryManager;
import com.rsupport.bucketlist.core.util.JwtUtils;
import com.rsupport.bucketlist.core.vo.HostSignInRequestVO;
import com.rsupport.bucketlist.auth.vo.HostSignInResponseVO;
import com.rsupport.bucketlist.auth.vo.HostSignUpCheckRequestVO;
import com.rsupport.bucketlist.auth.vo.HostSignUpCheckResponseVO;
import com.rsupport.bucketlist.core.base.BaseResponseVO;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.vo.HostSignUpRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignUpResponseVO;
import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.service.BucketlistManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

  @Autowired
  private CategoryManager categoryManager;

  @PostMapping(value = ApiUriConstants.SIGNUP_CHECK)
  public HostSignUpCheckResponseVO signUpCheck(@RequestBody HostSignUpCheckRequestVO requestVO) {
    User user = userManager.getUserByEmail(requestVO.getEmail());
    boolean signUp = (user != null);
    return new HostSignUpCheckResponseVO(signUp, user);
  }

  @PostMapping(value = ApiUriConstants.SIGNUP)
  public HostSignUpResponseVO signUp(@RequestBody HostSignUpRequestVO requestVO) {
    User user = userManager.signup(requestVO);
    return new HostSignUpResponseVO(user);
  }

  @PostMapping(value = ApiUriConstants.SIGNIN)
  public HostSignInResponseVO signIn(@RequestBody HostSignInRequestVO requestVO) {
    User user = userManager.signin(requestVO);
    String accessToken = jwtUtils.createAccessToken(user.getId());
    String refreshToken = jwtUtils.createRefreshToken(accessToken);
    return new HostSignInResponseVO(accessToken, refreshToken);
  }

  @AccessTokenCheck
  @PostMapping(value = ApiUriConstants.PROFILE)
  public BaseResponseVO createProfile(CreateProfileRequestVO requestVO) {
    userManager.createProfile(requestVO);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @GetMapping(value = ApiUriConstants.HOME)
  public HomeResponseVO home(HomeRequestVO requestVO) {
    List<Bucketlist> bucketlists = bucketlistManager.getBucketlists(requestVO);

    boolean popupYn = false;
    String popupPeriodStr = "1,7";
    for (String popupPeriod : popupPeriodStr.split(",")) {
      popupYn = bucketlistManager.existsPopupBucketlist(requestVO.getUserId(), Integer.parseInt(popupPeriod));
      if (popupYn)
        break;
    }
    return new HomeResponseVO(bucketlists, popupYn);
  }

  @AccessTokenCheck
  @GetMapping(value = ApiUriConstants.D_DAY)
  public DDayResponseVO dDay(DDayRequestVO requestVO) {
    List<DDayResponseVO.DDayVO> dDayVOList = new ArrayList<>();
    List<Bucketlist> dDayBucketlists = bucketlistManager.getDDayBucketlist(requestVO.getUserId(), requestVO.getFilter());

    int day = -1;
    for (Bucketlist bucketlist : dDayBucketlists) {
      if (day != bucketlist.getDDay()) {
        List<Bucketlist> bucketlists = bucketlistManager.getBucketlistsByDDate(bucketlist.getDDate());
        DDayResponseVO.DDayVO dDayVO = new DDayResponseVO.DDayVO(bucketlist.getDDay(), bucketlists);
        dDayVOList.add(dDayVO);
      }

      day = bucketlist.getDDay();
    }

    return new DDayResponseVO(dDayVOList);
  }

  @AccessTokenCheck
  @PostMapping(value = ApiUriConstants.BUCKETLIST_COMPLETE)
  public BaseResponseVO completeBucketlist(@RequestBody CompleteBucketlistRequestVO requestVO) {
    Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
    bucketlist.setUserCount(bucketlist.getUserCount() + 1);

    if (bucketlist.getUserCount() >= bucketlist.getGoalCount())
      bucketlist.setStatus(CommonCodes.BucketlistStatus.COMPLETED);

    bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @PostMapping(value = ApiUriConstants.BUCKETLIST_CANCEL)
  public BaseResponseVO cancelBucketlist(@RequestBody CancelBucketlistRequestVO requestVO) {
    Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
    bucketlist.setUserCount(bucketlist.getUserCount() - 1);

    if (bucketlist.getUserCount() < bucketlist.getGoalCount())
      bucketlist.setStatus(CommonCodes.BucketlistStatus.STARTED);

    bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @PostMapping(value = ApiUriConstants.BUCKETLIST_PIN)
  public BaseResponseVO pin(@RequestBody PinBucketlistRequestVO requestVO) {
    Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
    if (bucketlist.isPin()) {
      bucketlist.setPin(false);
    } else {
      bucketlist.setPin(true);
    }

    bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @GetMapping(value = ApiUriConstants.BUCKETLIST_BEFORE_WRITE)
  public BeforeWriteResponseVO beforeWrite(String userId) {
    User user = userManager.getUserById(userId);
    List<Category> categoryList = categoryManager.getCategoryListByUserId(user.getId());
    return new BeforeWriteResponseVO(categoryList);
  }

  @AccessTokenCheck
  @PostMapping(value = ApiUriConstants.BUCKETLIST_WRITE)
  public BaseResponseVO writeBucketlist(BucketlistWriteRequestVO requestVO) {
    bucketlistManager.writeBucketlist(requestVO);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @GetMapping(value = ApiUriConstants.BUCKETLIST_CRUD)
  public BucketlistViewResponseVO getBucketlist(@PathVariable String id, String userId) {
    Bucketlist bucketlist = bucketlistManager.getBucketlistById(id);
    return new BucketlistViewResponseVO(bucketlist);
  }

  @AccessTokenCheck
  @PostMapping(value = ApiUriConstants.BUCKETLIST_CRUD)
  public BaseResponseVO modifyBucketlist(@PathVariable String id, BucketlistModifyRequestVO requestVO) {
    bucketlistManager.modifyBucketlist(requestVO);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @DeleteMapping(value = ApiUriConstants.BUCKETLIST_CRUD)
  public BaseResponseVO removeBucketlist(@PathVariable String id, @RequestBody BucketlistRemoveRequestVO requestVO) {
    bucketlistManager.deleteBucketlist(id);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @GetMapping(value = ApiUriConstants.MYPAGE)
  public MyPageResponseVO mypage(MyPageRequestVO requstVO) {
    User user = userManager.getUserById(requstVO.getUserId());

    MyPageResponseVO responseVO = new MyPageResponseVO();
    responseVO.setName(user.getName());
    responseVO.setImageUrl(user.getImgUrl());

    int startedCount = bucketlistManager.getStartedBucklistCount(user.getId());
    responseVO.setStartedCount(startedCount);

    int completedCount = bucketlistManager.getCompletedBucketlistCount(user.getId());
    responseVO.setCompletedCount(completedCount);

    int dDayCount = bucketlistManager.getDDayBucketlist(user.getId(), null).size();
    responseVO.setDDayCount(dDayCount);

    List<MyPageResponseVO.CategoryVO> categoryList = setCategoryList(user);
    responseVO.setCategoryList(categoryList);

    responseVO.setReturnCode(ApiReturnCodes.OK);
    return responseVO;
  }

  private List<MyPageResponseVO.CategoryVO> setCategoryList(User user) {
    List<MyPageResponseVO.CategoryVO> categoryList = new ArrayList<>();
    List<Category> userCategoryList = categoryManager.getCategoryListByUserId(user.getId());
    for (Category category : userCategoryList) {
      int categoryCount = 0;
      for (Bucketlist bucketlist : user.getBucketlists()) {
        if (category.equals(bucketlist.getCategory())) {
          categoryCount++;
        }
      }
      MyPageResponseVO.CategoryVO categoryVO = new MyPageResponseVO.CategoryVO(category.getId(), category.getName(), categoryCount);
      categoryList.add(categoryVO);
    }
    return categoryList;
  }

  @AccessTokenCheck
  @GetMapping(value = ApiUriConstants.CATEGORY)
  public CategoryResponseVO getCategory(CategoryRequestVO requestVO) {
    List<Bucketlist> bucketlists = bucketlistManager.getBucketlistByCategoryId(requestVO.getCategoryId());
    return new CategoryResponseVO(bucketlists);
  }

  @AccessTokenCheck
  @PostMapping(value = ApiUriConstants.CATEGORY)
  public BaseResponseVO createCategory(@RequestBody CreateCategoryRequestVO requestVO) {
    User user = userManager.getUserById(requestVO.getUserId());

    Category category = new Category();
    category.setName(requestVO.getName());
    category.setPriority(categoryManager.getLastPriorityCategory(requestVO.getUserId()) + 1);
    category.setUser(user);
    categoryManager.save(category);

    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @PostMapping(value = ApiUriConstants.CATEGORY_EDIT_NAME)
  public BaseResponseVO modifyCategoryName(@RequestBody ModifyCategoryNameRequestVO requestVO) {
    categoryManager.modifyName(requestVO);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @PostMapping(value = ApiUriConstants.CATEGORY_EDIT_PRIORITY)
  public BaseResponseVO modifyCategoryPriority(@RequestBody ModifyCategoryPriorityRequestVO requestVO) {
    categoryManager.modifyPriority(requestVO);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @DeleteMapping(value = ApiUriConstants.CATEGORY)
  public BaseResponseVO removeCategory(@RequestBody RemoveCategoryRequestVO requestVO) {
    categoryManager.remove(requestVO);
    return BaseResponseVO.ok();
  }

  @AccessTokenCheck
  @DeleteMapping(value = ApiUriConstants.WITHDRAWAL)
  public BaseResponseVO withdrawal(@RequestBody WithdrawalRequestVO requestVO) {
    userManager.remove(requestVO.getUserId());
    return BaseResponseVO.ok();
  }
}
