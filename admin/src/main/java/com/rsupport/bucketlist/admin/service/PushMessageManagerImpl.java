package com.rsupport.bucketlist.admin.service;

import com.rsupport.bucketlist.core.domain.PushMessage;
import com.rsupport.bucketlist.core.repository.PushMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pushMessageManager")
public class PushMessageManagerImpl implements PushMessageManager {

  @Autowired
  private PushMessageRepository pushMessageRepository;

  @Override
  public PushMessage getPushMessage() {
    return pushMessageRepository.getPushMessage();
  }
}
