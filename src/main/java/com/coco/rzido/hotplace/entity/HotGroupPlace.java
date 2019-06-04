package com.coco.rzido.hotplace.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

/**
 * HotGroupPlace
 */
@Entity
@IdClass(HotGroupPlaseId.class)
@Setter @Getter
public class HotGroupPlace {

    @JsonBackReference
    @Id
    @ManyToOne
    @JoinColumn(name="hot_group_id")
    private HotGroup hotGroup;
    
    @Id
    @ManyToOne
    @JoinColumn(name="hot_place_id")
    private HotPlace hotPlace;

}