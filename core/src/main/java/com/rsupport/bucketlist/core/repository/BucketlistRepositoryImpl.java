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

    query.from(bucketlist)
            .where(bucketlist.user().id.eq(userId))
            .orderBy(bucketlist.pin.desc());

    return query.list(bucketlist);
  }

  @Override
  public boolean existsPopupBucketlist(String userId, int popupPeriod) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist)
            .where(bucketlist.user().id.eq(userId)
                    .and(bucketlist.dDate.eq(DateUtil.addDays(DateUtil.getDate(), popupPeriod))));

    return query.exists();
  }

  @Override
  public List<Bucketlist> getDDayBucketlists(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist)
            .where(bucketlist.dDate.isNotNull())
            .orderBy(bucketlist.dDate.asc())
            .groupBy(bucketlist.dDate);

    return query.list(bucketlist);
  }

  @Override
  public String getLastBucketlistId() {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist);

    return query.singleResult(bucketlist.id);
  }
}
