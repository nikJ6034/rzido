package com.coco.rzido.hotplace.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.coco.rzido.common.entity.BaseEntity;
import com.coco.rzido.member.entity.Member;

import org.codehaus.jackson.annotate.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

/**
 * HotPlaceComment
 */
@Entity
@Setter @Getter
public class HotPlaceComment extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @JsonBackReference
    @ManyToOne
    private HotPlace hotPlace;

    private String comment;

}