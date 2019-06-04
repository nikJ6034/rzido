package com.coco.rzido.role.entity;

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
public class Role extends BaseEntity{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String roleName;
}
