package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.NotAudited;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "mt_user", uniqueConstraints = @UniqueConstraint(name = "email", columnNames = "email"))
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate", "lastLoginDate", "enabled", "categories"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class User {

  @Id
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")
  private String id;

  @Column
  private String email;

  @Column(name = "account_type")
  private int accountType;

  @Column(name = "name")
  private String name;

  @Column(name = "img_url")
  private String imgUrl;

  @Column(name = "created_dt")
  private Date createdDate;

  @Column(name = "update_dt")
  private Date updatedDate;

  @Column(name = "last_login_dt")
  private Date lastLoginDate;

  @Column
  @ColumnDefault("1")
  private boolean enabled;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Category> categoryList = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Bucketlist> bucketlists = new ArrayList<>();
}
