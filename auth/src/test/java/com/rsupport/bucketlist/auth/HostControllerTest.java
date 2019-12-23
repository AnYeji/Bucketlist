package com.rsupport.bucketlist.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsupport.bucketlist.auth.constants.ApiUriConstants;
import com.rsupport.bucketlist.auth.vo.BucketlistRemoveRequestVO;
import com.rsupport.bucketlist.auth.vo.CreateCategoryRequestVO;
import com.rsupport.bucketlist.auth.vo.HostSignUpCheckRequestVO;
import com.rsupport.bucketlist.auth.vo.RefreshTokenRequestVO;
import com.rsupport.bucketlist.auth.vo.WithdrawalRequestVO;
import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.repository.BucketlistRepository;
import com.rsupport.bucketlist.core.repository.UserRepository;
import com.rsupport.bucketlist.auth.vo.CompleteBucketlistRequestVO;
import com.rsupport.bucketlist.auth.vo.PinBucketlistRequestVO;
import com.rsupport.bucketlist.core.repository.CategoryRepository;
import com.rsupport.bucketlist.core.service.BucketlistManager;
import com.rsupport.bucketlist.core.util.DateUtil;
import com.rsupport.bucketlist.core.util.JwtUtils;
import com.rsupport.bucketlist.core.vo.HostSignInRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignUpRequestVO;
import com.rsupport.bucketlist.core.vo.ModifyCategoryNameRequestVO;
import com.rsupport.bucketlist.core.vo.ModifyCategoryPriorityRequestVO;
import com.rsupport.bucketlist.core.vo.RemoveCategoryRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HostControllerTest {

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private ObjectMapper objectMapper;

  private MockMvc mockMvc;

  @Autowired
  private BucketlistManager bucketlistManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BucketlistRepository bucketlistRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private JwtUtils jwtUtils;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
            .apply(documentationConfiguration(this.restDocumentation))
            .alwaysDo(document("{method-name}",
                    preprocessRequest(
                            modifyUris()
                                    .scheme("http")
                                    .host("www.my-bury.com")
                                    .removePort(),
                            prettyPrint()
                    ),
                    preprocessResponse(prettyPrint())))
            .build();
  }

  @Test
  public void testSignUpCheck() throws Exception {
    HostSignUpCheckRequestVO requestVO = new HostSignUpCheckRequestVO();
    requestVO.setEmail("abc@def.com");

    this.mockMvc.perform(post(ApiUriConstants.SIGNUP_CHECK)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testSignUp() throws Exception {
    HostSignUpRequestVO requestVO = new HostSignUpRequestVO();
    requestVO.setEmail("ghi@jkl.com");

    this.mockMvc.perform(post(ApiUriConstants.SIGNUP)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testSignIn() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    HostSignInRequestVO requestVO = new HostSignInRequestVO();
    requestVO.setUserId(user.getId());

    this.mockMvc.perform(post(ApiUriConstants.SIGNIN)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testCreateProfile() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    File file = new File("D:\\dev-bucketlist\\workspace\\bucketlist\\auth\\src\\main\\resources\\static\\img\\test.jpg");
    FileInputStream fis = new FileInputStream(file);
    MockMultipartFile mockFile = new MockMultipartFile("profile", "test.jpg", "image/jpg", IOUtils.toByteArray(fis));

    this.mockMvc.perform(multipart(ApiUriConstants.PROFILE)
            /*.file(mockFile)*/
            .header("X-Auth-Token", accessToken)
            .param("userId", user.getId())
            .param("name", "lion")
            .param("defaultImg", "true"))
            .andExpect(status().isOk());
  }

  @Test
  public void testHome() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category = categoryRepository.save(category);

    Bucketlist bucketlist = new Bucketlist();
    bucketlist.setTitle("세계일주");
    bucketlist.setStatus("1");
    bucketlist.setUser(user);
    bucketlist.setCategory(category);
    bucketlistRepository.save(bucketlist);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    this.mockMvc.perform(get(ApiUriConstants.HOME)
            .header("X-Auth-Token", accessToken)
            .param("userId", user.getId())
            .param("filter", "started")
            .param("sort", "updatedDt")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            /*.andDo(document("test-home",
                    responseFields(
                            fieldWithPath("popupYn").type(JsonFieldType.NUMBER).description("팝업 여부")
                    )
            ))*/;
  }

  @Test
  public void testDDay() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category = categoryRepository.save(category);

    Bucketlist bucketlist = new Bucketlist();
    bucketlist.setTitle("세계일주");
    bucketlist.setStatus("1");
    bucketlist.setDDate(DateUtils.addDays(DateUtil.getDate(), 100));
    bucketlist.setUser(user);
    bucketlist.setCategory(category);
    bucketlistRepository.save(bucketlist);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    this.mockMvc.perform(get(ApiUriConstants.D_DAY)
            .header("X-Auth-Token", accessToken)
            .param("userId", user.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            /*.andDo(document("host-dDay"))*/;
  }

  @Test
  public void testCompleteBucketlist() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    CompleteBucketlistRequestVO requestVO = new CompleteBucketlistRequestVO();
    requestVO.setUserId(user.getId());

    String bucketlistId = bucketlistManager.getLastBucketlistId();
    requestVO.setBucketlistId(bucketlistId);

    this.mockMvc.perform(post(ApiUriConstants.BUCKETLIST_COMPLETE)
            .header("X-Auth-Token", accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk())
            /*.andDo(document("host-completeBucketlist"))*/;
  }

  @Test
  public void testPinBucketlist() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    PinBucketlistRequestVO requestVO = new PinBucketlistRequestVO();
    requestVO.setUserId(user.getId());

    String bucketlistId = bucketlistManager.getLastBucketlistId();
    requestVO.setBucketlistId(bucketlistId);

    this.mockMvc.perform(post(ApiUriConstants.BUCKETLIST_PIN)
            .header("X-Auth-Token", accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk())
            /*.andDo(document("host-pinBucketlist"))*/;
  }

  @Test
  public void testBeforeWrite() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category.setName("category01");
    category.setUser(user);
    categoryRepository.save(category);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    this.mockMvc.perform(get(ApiUriConstants.BUCKETLIST_BEFORE_WRITE)
            .header("X-Auth-Token", accessToken)
            .param("userId", user.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void testWrite() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category = categoryRepository.save(category);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    File file = new File("D:\\dev-bucketlist\\workspace\\bucketlist\\auth\\src\\main\\resources\\static\\img\\test.jpg");
    FileInputStream fis = new FileInputStream(file);
    MockMultipartFile mockFile1 = new MockMultipartFile("profile", "test.jpg", "image/jpg", IOUtils.toByteArray(fis));
    MockMultipartFile mockFile2 = new MockMultipartFile("profile", "test.jpeg", "image/jpg", IOUtils.toByteArray(fis));
    MockMultipartFile mockFile3 = new MockMultipartFile("profile", "test.jpeg", "image/jpg", IOUtils.toByteArray(fis));

    this.mockMvc.perform(multipart(ApiUriConstants.BUCKETLIST_WRITE)
            /*.file(mockFile1)
            .file(mockFile2)
            .file(mockFile3)*/
            .header("X-Auth-Token", accessToken)
            .param("title", "eat pray love")
            .param("open", "true")
            .param("dDate", "2019-12-25")
            .param("goalCount", "10")
            .param("memo", "Memo!!")
            .param("categoryId", category.getId())
            .param("userId", user.getId()))
            .andExpect(status().isOk());
  }

  @Test
  public void testGetBucketlist() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category = categoryRepository.save(category);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    Bucketlist bucketlist = new Bucketlist();
    bucketlist.setTitle("세계일주");
    bucketlist.setUser(user);
    bucketlist.setCategory(category);
    bucketlist = bucketlistRepository.save(bucketlist);

    this.mockMvc.perform(get(StringUtils.replace(ApiUriConstants.BUCKETLIST_CRUD, "{id}", bucketlist.getId()))
            .header("X-Auth-Token", accessToken)
            .param("userId", user.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void testModifyBucketlist() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category = categoryRepository.save(category);

    Bucketlist bucketlist = new Bucketlist();
    bucketlist.setTitle("세계일주");
    bucketlistRepository.save(bucketlist);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    File file = new File("D:\\dev-bucketlist\\workspace\\bucketlist\\auth\\src\\main\\resources\\static\\img\\test.jpg");
    FileInputStream fis = new FileInputStream(file);
    MockMultipartFile mockFile1 = new MockMultipartFile("profile", "test.jpg", "image/jpg", IOUtils.toByteArray(fis));
    MockMultipartFile mockFile2 = new MockMultipartFile("profile", "test.jpeg", "image/jpg", IOUtils.toByteArray(fis));
    MockMultipartFile mockFile3 = new MockMultipartFile("profile", "test.jpeg", "image/jpg", IOUtils.toByteArray(fis));

    this.mockMvc.perform(multipart(StringUtils.replace(ApiUriConstants.BUCKETLIST_CRUD, "{id}", bucketlist.getId()))
            /*.file(mockFile1)
            .file(mockFile2)
            .file(mockFile3)*/
            .header("X-Auth-Token", accessToken)
            .param("title", "eat pray love")
            .param("open", "true")
            .param("dDate", "2019-12-25")
            .param("goalCount", "10")
            .param("memo", "Memo!!")
            .param("noImg1", "true")
            .param("noImg2", "true")
            .param("noImg3", "true")
            .param("categoryId", category.getId())
            .param("userId", user.getId()))
            .andExpect(status().isOk());
  }

  @Test
  public void testRemoveBucketlist() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category = categoryRepository.save(category);

    Bucketlist bucketlist = new Bucketlist();
    bucketlist.setTitle("세계일주");
    bucketlist.setUser(user);
    bucketlist.setCategory(category);
    bucketlist = bucketlistRepository.save(bucketlist);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    BucketlistRemoveRequestVO requestVO = new BucketlistRemoveRequestVO();
    requestVO.setUserId(user.getId());

    this.mockMvc.perform(MockMvcRequestBuilders
            .delete(StringUtils.replace(ApiUriConstants.BUCKETLIST_CRUD, "{id}", bucketlist.getId()))
            .header("X-Auth-Token", accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testMyPage() throws Exception {
    User user = new User();
    user.setName("my name");
    user.setImgUrl("https://item.kakaocdn.net/do/877826b74e9c40647995f7cf7fd795b8f43ad912ad8dd55b04db6a64cddaf76d");
    user = userRepository.save(user);

    Category category = new Category();
    category.setName("category01");
    category.setUser(user);
    categoryRepository.save(category);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    this.mockMvc.perform(get(ApiUriConstants.MYPAGE)
            .header("X-Auth-Token", accessToken)
            .param("userId", user.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void testGetCategory() throws Exception {
      User user = new User();
      user = userRepository.save(user);

      Category category = new Category();
      category = categoryRepository.save(category);

      Bucketlist bucketlist = new Bucketlist();
      bucketlist.setTitle("세계일주");
      bucketlist.setUser(user);
      bucketlist.setCategory(category);
      bucketlistRepository.save(bucketlist);

    String accessToken = jwtUtils.createAccessToken(user.getId());

      this.mockMvc.perform(get(ApiUriConstants.CATEGORY)
              .header("X-Auth-Token", accessToken)
              .param("userId", user.getId())
              .param("categoryId", category.getId())
              .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isOk());
  }

  @Test
  public void testCreateCategory() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category.setUser(user);
    categoryRepository.save(category);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    CreateCategoryRequestVO requestVO = new CreateCategoryRequestVO();
    requestVO.setUserId(user.getId());
    requestVO.setName("new category");

    this.mockMvc.perform(post(ApiUriConstants.CATEGORY)
            .header("X-Auth-Token", accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testModifyNameCategory() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category.setName("category name");
    category.setUser(user);
    category = categoryRepository.save(category);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    ModifyCategoryNameRequestVO requestVO = new ModifyCategoryNameRequestVO();
    requestVO.setUserId(user.getId());
    requestVO.setId(category.getId());
    requestVO.setName(category.getName());

    this.mockMvc.perform(post(ApiUriConstants.CATEGORY_EDIT_NAME)
            .header("X-Auth-Token", accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testModifyPriorityCategory() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category.setUser(user);
    category = categoryRepository.save(category);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    ModifyCategoryPriorityRequestVO requestVO = new ModifyCategoryPriorityRequestVO();
    requestVO.setUserId(user.getId());

    List<String> categoryIdList = new ArrayList<>();
    categoryIdList.add(category.getId());
    requestVO.setCategoryIdList(categoryIdList);

    this.mockMvc.perform(post(ApiUriConstants.CATEGORY_EDIT_PRIORITY)
            .header("X-Auth-Token", accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testRemoveCategory() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    Category category = new Category();
    category = categoryRepository.save(category);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    RemoveCategoryRequestVO requestVO = new RemoveCategoryRequestVO();
    requestVO.setUserId(user.getId());

    List<String> categoryIdList = new ArrayList<>();
    categoryIdList.add(category.getId());
    requestVO.setCategoryIdList(categoryIdList);

    this.mockMvc.perform(MockMvcRequestBuilders.delete(ApiUriConstants.CATEGORY)
            .header("X-Auth-Token", accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testWithdrawal() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    String accessToken = jwtUtils.createAccessToken(user.getId());

    WithdrawalRequestVO requestVO = new WithdrawalRequestVO();
    requestVO.setUserId(user.getId());

    this.mockMvc.perform(MockMvcRequestBuilders.delete(ApiUriConstants.WITHDRAWAL)
            .header("X-Auth-Token", accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testRefreshToken() throws Exception {
    User user = new User();
    user = userRepository.save(user);

    String accessToken = jwtUtils.createAccessToken(user.getId());
    String refreshToken = jwtUtils.createRefreshToken(accessToken);

    RefreshTokenRequestVO requestVO = new RefreshTokenRequestVO(user.getId(), refreshToken);

    this.mockMvc.perform(post(ApiUriConstants.REFRESH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }
}
