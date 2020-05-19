package com.rsupport.bucketlist.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass()
public abstract class BaseTimestampEntity<K extends Serializable> implements Serializable{

    @JsonIgnore
    @Column(name = "created_dt", insertable = true, updatable = false)
    private Date createdDt;

    @JsonIgnore
    @Column(name = "updated_dt", insertable = true, updatable = true)
    private Date updatedDt;
}
