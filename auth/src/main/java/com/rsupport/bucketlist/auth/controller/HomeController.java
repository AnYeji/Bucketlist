package com.rsupport.bucketlist.auth.controller;

import com.rsupport.bucketlist.auth.constants.ApiUriConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping("/")
  public String index() {
    return "home";
  }

  @RequestMapping(value = ApiUriConstants.PRIVACY_POLICY)
  public String privacyPolicy() {
    return "privacy_policy";
  }

  @RequestMapping(value = ApiUriConstants.TERMS_OF_USE)
  public String termsOfUse() {
    return "terms_of_use";
  }

}
