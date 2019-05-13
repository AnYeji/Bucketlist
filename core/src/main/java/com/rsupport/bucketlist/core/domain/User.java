package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.NotAudited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "mt_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate", "lastLoginDate", "enabled", "categories"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class User {

  @Id
  /*@GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")*/
  private String id;

  @Column
  private String email;

  @Column(name = "account_type")
  private int accountType;

  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "img_url")
  private String imgUrl;

  @Column(name = "created_dt")
  private Date createdDate;

  @Column(name = "update_dt")
  private Date updatedDate;

  @Column(name = "last_login_dt")
  private Date lastLoginDate;

  @Column
  private boolean enabled;

  @OneToMany(mappedBy = "user")
  private List<Category> categoryList = new ArrayList<>();

}
