package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Entity
@Table(name = "mt_bucketlist")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bucketlist {

  @Id
  /*@GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")*/
  private String id;

  @Lob
  @Column(nullable = false)
  private String title;

  @ColumnDefault("0")
  private boolean open;

  private boolean complete;

  @Column(name = "d_date")
  @JsonIgnore
  private Date dDate;

  @Transient
  @JsonProperty("d_day")
  private Integer dDay;

  @Column(name = "user_count")
  @JsonProperty("user_count")
  private Integer userCount;

  @Column(name = "goal_count")
  @JsonProperty("goal_count")
  private Integer goalCount;

  @Lob
  private String memo;

  @Column(name = "img_url_1")
  @JsonProperty("img_url_1")
  private String imgUrl1;

  @Column(name = "img_url_2")
  @JsonProperty("img_url_2")
  private String imgUrl2;

  @Column(name = "img_url_3")
  @JsonProperty("img_url_3")
  private String imgUrl3;

  @Column(name = "created_dt")
  @JsonIgnore
  private Date createdDate;

  @Column(name = "updated_dt")
  @JsonIgnore
  private Date updatedDate;

  @OneToOne
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @JsonIgnore
  private User user;
}
