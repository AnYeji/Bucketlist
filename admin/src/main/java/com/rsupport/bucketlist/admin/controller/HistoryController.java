package com.rsupport.bucketlist.admin.controller;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.repository.HistoryRevisionRepository;
import com.rsupport.bucketlist.core.service.BucketlistManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HistoryController {

  @Autowired
  private BucketlistManager bucketlistManager;

  @Autowired
  private HistoryRevisionRepository historyRevisionRepository;

  @ResponseBody
  @GetMapping(value = "/audit_test")
  public String auditTest() {
    Bucketlist bucketlist = new Bucketlist();
    bucketlist.setTitle("title1");
    bucketlist = bucketlistManager.saveBucketlist(bucketlist);

    bucketlistManager.deleteBucketlist(bucketlist.getId());

    return bucketlist.getId();
  }

  @ResponseBody
  @RequestMapping(value = "/history/{id}")
  public String historyView(@PathVariable String id) {
    historyRevisionRepository.getPreviousRevisionNumber(Bucketlist.class, id);
    historyRevisionRepository.getHistoryListWithJoinField(Bucketlist.class);
    return "history";
  }
}
