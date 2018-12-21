package com.rsupport.bucketlist.core.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import com.rsupport.bucketlist.core.domain.QUser;
import com.rsupport.bucketlist.core.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public User getUserByToken(String token) {
    JPAQuery query = new JPAQuery(entityManager);
    QUser user = QUser.user;

    query.from(user).where(user.token.eq(token));
    return query.singleResult(user);
  }
}
