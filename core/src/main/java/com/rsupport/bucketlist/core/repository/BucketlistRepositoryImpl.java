package com.rsupport.bucketlist.core.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.domain.QBucketlist;
import com.rsupport.bucketlist.core.domain.QUser;
import com.rsupport.bucketlist.core.util.DateUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("bucketlistRepository")
public class BucketlistRepositoryImpl implements BucketlistRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Bucketlist> getBucketlistsByUserId(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(bucketlist.user().id.eq(userId));
    return query.list(bucketlist);
  }

  @Override
  public boolean existsLessThanThreeDaysBucketlist(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QUser user = QUser.user;
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(user).join(bucketlist).where(user.id.eq(userId).and(bucketlist.dDate.loe(DateUtil.addDays(DateUtil.getDate(), 3))));
    return query.exists();
  }
}
