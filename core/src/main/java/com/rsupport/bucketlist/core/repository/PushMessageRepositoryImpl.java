package com.rsupport.bucketlist.core.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import com.rsupport.bucketlist.core.domain.PushMessage;
import com.rsupport.bucketlist.core.domain.QPushMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PushMessageRepositoryImpl implements PushMessageRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public PushMessage getPushMessage() {
    JPAQuery query = new JPAQuery(entityManager);
    QPushMessage pushMessage = QPushMessage.pushMessage;

    query.from(pushMessage);
    return query.singleResult(pushMessage);
  }
}
