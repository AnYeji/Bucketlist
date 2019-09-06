package com.rsupport.bucketlist.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsupport.bucketlist.auth.constants.ApiUriConstants;
import com.rsupport.bucketlist.auth.vo.BucketlistWriteRequestVO;
import com.rsupport.bucketlist.auth.vo.CompleteBucketlistRequestVO;
import com.rsupport.bucketlist.auth.vo.PinBucketlistRequestVO;
import com.rsupport.bucketlist.core.service.BucketlistManager;
import com.rsupport.bucketlist.core.service.CategoryManager;
import com.rsupport.bucketlist.core.util.DateUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
/*@AutoConfigureRestDocs(uriScheme = "http", uriHost = "10.1.101.161", uriPort = 80)*/
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
  private CategoryManager categoryManager;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
            .apply(documentationConfiguration(this.restDocumentation))
            .alwaysDo(document("{method-name}",
                    preprocessRequest(
                            modifyUris()
                                    .scheme("http")
                                    .host("10.1.101.161")
                                    .removePort(),
                            prettyPrint()
                    ),
                    preprocessResponse(prettyPrint())))
            .build();
  }

  @Test
  public void testHome() throws Exception {
    this.mockMvc.perform(get(ApiUriConstants.HOST_HOME)
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
    this.mockMvc.perform(get(ApiUriConstants.HOST_D_DAY)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            /*.andDo(document("host-dDay"))*/;
  }

  @Test
  public void testCompleteBucketlist() throws Exception {
    CompleteBucketlistRequestVO requestVO = new CompleteBucketlistRequestVO();
    String bucketlistId = bucketlistManager.getLastBucketlistId();
    requestVO.setBucketlistId(bucketlistId);

    this.mockMvc.perform(post(ApiUriConstants.HOST_BUCKETLIST_COMPLETE)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk())
            /*.andDo(document("host-completeBucketlist"))*/;
  }

  @Test
  public void testPinBucketlist() throws Exception {
    PinBucketlistRequestVO requestVO = new PinBucketlistRequestVO();
    String bucketlistId = bucketlistManager.getLastBucketlistId();
    requestVO.setBucketlistId(bucketlistId);

    this.mockMvc.perform(post(ApiUriConstants.HOST_BUCKETLIST_PIN)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk())
            /*.andDo(document("host-pinBucketlist"))*/;
  }

  @Test
  public void testBeforeWrite() throws Exception {
    this.mockMvc.perform(get(ApiUriConstants.HOST_BUCKETLIST_BEFORE_WRITE)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void testWrite() throws Exception {
    String categoryId = categoryManager.getLastCategoryId();

    BucketlistWriteRequestVO requestVO = new BucketlistWriteRequestVO();
    requestVO.setTitle("eat pray love");
    requestVO.setOpen(true);
    requestVO.setGoalCount(10);
    requestVO.setDDate(DateUtil.addDays(DateUtil.getDate(), 3));
    requestVO.setMemo("memo");
    requestVO.setCategoryName("ETC");
    requestVO.setUserId("user01");

    this.mockMvc.perform(post(ApiUriConstants.HOST_BUCKETLIST_WRITE)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk());
  }

  @Test
  public void testImageUpload() throws Exception {
    this.mockMvc.perform(post(ApiUriConstants.HOST_IMAGE_UPLOAD)
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  public void testGetBucketlist() throws Exception {
    /*this.mockMvc.perform(get(StringUtils.replace(ApiUriConstants.HOST_BUCKETLIST_CRUD, "{bucketlistId}", "8a81e5216b88eaab016b88eac1cd0005"))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());*/
  }

  @Test
  public void testModifyBucketlist() throws Exception {

  }
}
