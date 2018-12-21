package com.rsupport.bucketlist.admin.controller;

import com.rsupport.bucketlist.admin.push.FcmSender;
import com.rsupport.bucketlist.admin.service.PushMessageManager;
import com.rsupport.bucketlist.core.domain.PushMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Slf4j
@Controller
public class PushMessageController {

  @Autowired
  private PushMessageManager pushMessageManager;

  @Autowired
  private FcmSender fcmSender;

  @RequestMapping("/test")
  public String test(){
    return "pushMessage/test";
  }

  @RequestMapping("/send/push")
  public String sendPush(String token) {
    log.info("token : " + token);
    PushMessage pushMessage = pushMessageManager.getPushMessage();
    fcmSender.sendMessage(token, pushMessage, new Date().getTime());
    return "pushMessage/result";
  }

}
