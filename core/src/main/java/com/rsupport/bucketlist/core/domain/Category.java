package com.rsupport.bucketlist.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mt_category")
public class Category {

  @Id
  private String id;

  private String name;

  private User user;
}
