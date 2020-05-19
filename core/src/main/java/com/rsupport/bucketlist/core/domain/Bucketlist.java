package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rsupport.bucketlist.core.constants.ColumnEncryptionConstants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
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

@Getter
@Setter
@Entity
@Table(name = "mt_bucketlist")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class Bucketlist extends BaseTimestampEntity<String> {

  @Id
  @Column
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")
  private String id;

  @Lob
  @Column(nullable = false)
  @ColumnTransformer(read = ColumnEncryptionConstants.DEC_BUCKETLIST_TITLE, write = ColumnEncryptionConstants.ENC_COLUMN)
  private String title;

  @Column
  @ColumnDefault("0")
  private boolean open;

  @Column
  @ColumnDefault("0")
  private boolean pin;

  @Column
  private String status;

  @JsonIgnore
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
  @Column
  @ColumnTransformer(read = ColumnEncryptionConstants.DEC_BUCKETLIST_MEMO, write = ColumnEncryptionConstants.ENC_COLUMN)
  private String memo;

  @Column(name = "img_url_1")
  @ColumnTransformer(read = ColumnEncryptionConstants.DEC_BUCKETLIST_IMG_URL_1, write = ColumnEncryptionConstants.ENC_COLUMN)
  private String imgUrl1;

  @Column(name = "img_url_2")
  @ColumnTransformer(read = ColumnEncryptionConstants.DEC_BUCKETLIST_IMG_URL_2, write = ColumnEncryptionConstants.ENC_COLUMN)
  private String imgUrl2;

  @Column(name = "img_url_3")
  @ColumnTransformer(read = ColumnEncryptionConstants.DEC_BUCKETLIST_IMG_URL_3, write = ColumnEncryptionConstants.ENC_COLUMN)
  private String imgUrl3;

  @OneToOne
  private Category category;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Transient
  private List<MultipartFile> files;
}
