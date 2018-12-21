package com.rsupport.bucketlist.core.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "mt_push_message")
public class PushMessage {

  @Id
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")
  private String id;

  @Column(name = "admin_title")
  private String adminTitle;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "start_dt")
  private String startDate;

}
