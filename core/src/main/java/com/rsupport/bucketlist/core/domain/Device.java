package com.rsupport.bucketlist.core.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "mt_device")
public class Device {

  @Id
  private String id;

  @Column(name = "device_key")
  private String deviceKey;

  @Column(name = "app_version")
  private String appVersion;

  @Column(name = "push_id")
  private String pushId;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
