package com.coco.rzido.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.coco.rzido.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Api extends BaseEntity{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String url;
	
	@Column(length=1)
	private String readRole = "N";
	
	@Column(length=1)
	private String writeRole = "N";
	
	@Column(length=1)
	private String modifyRole = "N";
	
	@Column(length=1)
	private String deleteRole = "N";
}
