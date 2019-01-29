package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "mt_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

  @Id
  /*@GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")*/
  private String id;

  private String email;

  @Column(name = "account_type")
  @JsonProperty("account_type")
  private int accountType;

  @Column(name = "nick_name")
  @JsonProperty("nick_name")
  private String nickName;

  @Column(name = "img_url")
  @JsonProperty("img_url")
  private String imgUrl;

  @Column(name = "created_dt")
  @JsonIgnore
  private Date createdDate;

  @Column(name = "update_dt")
  @JsonIgnore
  private Date updatedDate;

  @Column(name = "logined_dt")
  @JsonIgnore
  private Date loginedDate;

  @JsonIgnore
  private boolean enabled;

  @OneToMany
  @JsonIgnore
  private List<Category> categories;
}
