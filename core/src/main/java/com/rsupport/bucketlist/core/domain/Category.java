package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rsupport.bucketlist.core.constants.ColumnEncryptionConstants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "mt_category", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "user_id"}))
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class Category extends BaseTimestampEntity<String> {

  @Id
  @Column
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @GeneratedValue(generator = "system-uuid")
  private String id;

  @Column
  @ColumnTransformer(read = ColumnEncryptionConstants.DEC_CATEGORY_NAME, write = ColumnEncryptionConstants.ENC_COLUMN)
  private String name;

  @Column
  private int priority;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
