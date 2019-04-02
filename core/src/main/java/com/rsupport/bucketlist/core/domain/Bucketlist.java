package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

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
import java.util.List;

@Data
@Entity
@Table(name = "mt_bucketlist")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"dDate", "createdDate", "updatedDate", "user"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
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

  @ColumnDefault("0")
  private boolean pin;

  @ColumnDefault("0")
  private boolean complete;

  @Column(name = "d_date")
  private Date dDate;

  @Transient
  private Integer dDay;

  @Column(name = "user_count")
  @ColumnDefault("0")
  private int userCount;

  @Column(name = "goal_count")
  @ColumnDefault("1")
  private int goalCount;

  @Lob
  private String memo;

  @Column(name = "img_url_1")
  private String imgUrl1;

  @Column(name = "img_url_2")
  private String imgUrl2;

  @Column(name = "img_url_3")
  private String imgUrl3;

  @Column(name = "created_dt")
  private Date createdDate;

  @Column(name = "updated_dt")
  private Date updatedDate;

  @OneToOne
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Transient
  private List<MultipartFile> files;
}
