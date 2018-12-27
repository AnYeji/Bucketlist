package com.rsupport.bucketlist.core.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.NotAudited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "mt_user")
public class User {

  @Id
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")
  private String id;

  private String email;

  @Column(name = "account_type")
  private int accountType;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "created_dt")
  private Date createdDate;

  @NotAudited
  @Column(name = "update_dt")
  private Date updatedDate;

  @Column(name = "logined_dt")
  private Date loginedDate;

  private boolean enabled;
}
