package com.rsupport.bucketlist.core.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

}
