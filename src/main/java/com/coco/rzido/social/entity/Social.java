package com.coco.rzido.social.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.coco.rzido.common.entity.BaseEntity;
import com.coco.rzido.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Social extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String kind;
    @Column(length = 100, nullable = false)
    private String socialKey;
    @ManyToOne
    private Member member;

}
