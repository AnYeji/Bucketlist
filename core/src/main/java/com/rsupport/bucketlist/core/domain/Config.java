package com.rsupport.bucketlist.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "zt_config")
public class Config {

  @Id
  private String id;

  private String value;
}
