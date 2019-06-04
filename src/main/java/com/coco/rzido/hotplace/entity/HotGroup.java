package com.coco.rzido.hotplace.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.coco.rzido.common.entity.BaseEntity;

import org.codehaus.jackson.annotate.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

/**
 * HotGroup
 */
@Entity
@Getter @Setter
public class HotGroup extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonManagedReference
	@OneToMany(mappedBy = "hotGroup")
	// @OrderBy("sortNumber asc")
	private List<HotGroupPlace> hotGroupPlace;
    
}