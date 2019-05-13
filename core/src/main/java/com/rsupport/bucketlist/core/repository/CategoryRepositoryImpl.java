package com.rsupport.bucketlist.core.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import com.rsupport.bucketlist.core.domain.QCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("categoryRepository")
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public String getLastCatoryId() {
    JPAQuery query = new JPAQuery(entityManager);
    QCategory category = QCategory.category;

    query.from(category);
    return query.singleResult(category.id);
  }
}
