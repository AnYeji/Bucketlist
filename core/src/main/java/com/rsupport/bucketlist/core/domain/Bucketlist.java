package com.rsupport.bucketlist.core.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Audited
@Table(name = "mt_bucketlist")
public class Bucketlist {

  @Id
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")
  private String id;

  @Lob
  private String title;

  private boolean open;

  private boolean status;

  private int category;

  @Column(name = "d_day")
  private Date dDay;

  @Column(name = "count")
  private int count;

  @Lob
  private String memo;

  @Column(name = "img_url_1")
  private String imgUrl1;

  @Column(name = "img_url_2")
  private String imgUrl2;

  @Column(name = "img_url_3")
  private String imgUrl3;

  @Column(name = "created_dt")
  private Date createdDate;

  @Column(name = "updated_dt")
  private Date updatedDate;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
  private User user;

}
