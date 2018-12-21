package com.rsupport.bucketlist.core.domain;

import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "zt_revision_info")
@RevisionEntity(RevisionListener.class)
public class HistoryRevisionEntity {

  @Id
  @RevisionNumber
  private long id;

  @RevisionTimestamp
  private long timestamp;

}
