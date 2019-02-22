package com.rsupport.bucketlist.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsupport.bucketlist.auth.constants.ApiUriConstants;
import com.rsupport.bucketlist.auth.vo.HostCompleteBucketlistRequestVO;
import com.rsupport.bucketlist.auth.vo.HostPinBucketlistRequestVO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
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

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
            .apply(documentationConfiguration(this.restDocumentation))
            .alwaysDo(document("{class-name}/{method-name}",
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
            /*.andDo(document("host-home",
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
    HostCompleteBucketlistRequestVO requestVO = new HostCompleteBucketlistRequestVO();
    requestVO.setBucketlistId("bucketlist01");
    this.mockMvc.perform(post(ApiUriConstants.HOST_COMPLETE_BUCKETLIST)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk())
            /*.andDo(document("host-completeBucketlist"))*/;
  }

  @Test
  public void testPinBucketlist() throws Exception {
    HostPinBucketlistRequestVO requestVO = new HostPinBucketlistRequestVO();
    requestVO.setBucketlistId("bucketlist01");
    this.mockMvc.perform(post(ApiUriConstants.HOST_PIN_BUCKETLIST)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestVO)))
            .andExpect(status().isOk())
            /*.andDo(document("host-pinBucketlist"))*/;
  }

  /*@Test
  public void testGetBucketlist() {
  
  }
  
  @Test
  public void testSaveBucketlist() {
  
  }*/

}

/*http://woowabros.github.io/experience/2018/12/28/spring-rest-docs.html*/
