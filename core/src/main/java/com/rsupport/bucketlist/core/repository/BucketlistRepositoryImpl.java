package com.rsupport.bucketlist.core.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import com.rsupport.bucketlist.core.constants.CommonCodes;
import com.rsupport.bucketlist.core.domain.Bucketlist;
import com.rsupport.bucketlist.core.domain.QBucketlist;
import com.rsupport.bucketlist.core.util.DateUtil;
import com.rsupport.bucketlist.core.vo.HomeRequestVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository("bucketlistRepository")
public class BucketlistRepositoryImpl implements BucketlistRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Bucketlist> getBucketlists(HomeRequestVO requestVO) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist)
            .where(bucketlist.user().id.eq(requestVO.getUserId()));

    if(!StringUtils.isBlank(requestVO.getFilter())) {
      if (requestVO.getFilter().equals("started"))
        query.where(bucketlist.status.eq("1"));

      if (requestVO.getFilter().equals("completed"))
        query.where(bucketlist.status.eq("2"));

      if(requestVO.getFilter().equals("all"))
        query.where(bucketlist.status.eq("1").or(bucketlist.status.eq("2")));
    }

    if(requestVO.getSort().equals("updatedDt"))
      query.orderBy(bucketlist.updatedDt.desc());

    if(requestVO.getSort().equals("createdDt"))
      query.orderBy(bucketlist.createdDt.asc());

    return query.list(bucketlist);
  }

  @Override
  public boolean existsPopupBucketlist(String userId, int popupPeriod) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist)
            .where(bucketlist.user().id.eq(userId)
                    .and(bucketlist.dDate.eq(DateUtil.addDays(DateUtil.getToday(), popupPeriod))));

    return query.exists();
  }

  @Override
  public List<Bucketlist> getDDayBucketlist(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist)
            .where(bucketlist.user().id.eq(userId)
                    .and(bucketlist.status.eq(CommonCodes.BucketlistStatus.STARTED))
                    .and(bucketlist.dDate.isNotNull())
                    .and(bucketlist.dDate.gt(DateUtil.getDate())))
            .orderBy(bucketlist.dDate.asc());

    return query.list(bucketlist);
  }

  @Override
  public List<Bucketlist> getBucketlistsByDDate(Date date) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(bucketlist.dDate.eq(date)
            .and(bucketlist.status.eq(CommonCodes.BucketlistStatus.STARTED)));

    return query.list(bucketlist);
  }

  @Override
  public String getLastBucketlistId() {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist);

    return query.singleResult(bucketlist.id);
  }

  @Override
  public int getStartedBucketlistCount(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(bucketlist.user().id.eq(userId)
            .and(bucketlist.status.eq(CommonCodes.BucketlistStatus.STARTED)));

    return (int)query.count();
  }

  @Override
  public int getCompletedBucketlistCount(String userId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist).where(bucketlist.user().id.eq(userId)
            .and(bucketlist.status.eq(CommonCodes.BucketlistStatus.COMPLETED)));

    return (int)query.count();
  }

  @Override
  public List<Bucketlist> getBucketlistByCategoryId(String categoryId) {
    JPAQuery query = new JPAQuery(entityManager);
    QBucketlist bucketlist = QBucketlist.bucketlist;

    query.from(bucketlist)
            .where(bucketlist.category().id.eq(categoryId))
            .orderBy(bucketlist.createdDt.asc());

    return query.list(bucketlist);
  }
}
