package com.rsupport.bucketlist.core.domain;

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
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")
  @JsonProperty
  private String id;

  @Lob
  @Column(nullable = false)
  @JsonProperty
  private String title;

  @ColumnDefault("0")
  @JsonProperty
  private boolean open;

  @JsonProperty
  private boolean complete;

  @OneToOne
  private Category category;

  @Column(name = "d_day")
  private Date dDate;

  @Column(name = "count")
  @JsonProperty
  private int count;

  @Lob
  @JsonProperty
  private String memo;

  @Column(name = "img_url_1")
  @JsonProperty
  private String imgUrl1;

  @Column(name = "img_url_2")
  @JsonProperty
  private String imgUrl2;

  @Column(name = "img_url_3")
  @JsonProperty
  private String imgUrl3;

  @Column(name = "created_dt")
  private Date createdDate;

  @Column(name = "updated_dt")
  private Date updatedDate;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Transient
  @JsonProperty
  private int dDay;
}
