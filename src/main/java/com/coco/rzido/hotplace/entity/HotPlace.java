package com.coco.rzido.hotplace.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.coco.rzido.attachFile.entity.AttachFileImage;
import com.coco.rzido.common.entity.BaseEntity;
import com.coco.rzido.member.entity.Member;

import org.codehaus.jackson.annotate.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

/**
 * hotplace
 */
@Entity
@Getter @Setter
public class HotPlace extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @Lob
    private String review;

    private Integer starPoint;

    private String address;

    private String addressDetail;

    private Integer likes;

    private Double lat;

    private Double lon;

    @JsonManagedReference
    @OneToMany(mappedBy = "hotPlace")
    private List<HotPlaceComment> hotPlaceComment;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "hatplace_image", joinColumns = @JoinColumn(name = "hatplace_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<AttachFileImage> attachFile;
    
}