package com.coco.rzido.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {

    @Column(nullable=false,length = 1)
    private String deleteYN = "N";

    @Column
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate = new Date();

    @Column(updatable=false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate = new Date();
}
