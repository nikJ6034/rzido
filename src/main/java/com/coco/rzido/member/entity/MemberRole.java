package com.coco.rzido.member.entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.coco.rzido.common.entity.BaseEntity;
import com.coco.rzido.role.entity.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
//@Data
//@IdClass(MemberRoleKey.class)
public class MemberRole extends BaseEntity{

//	@EmbeddedId
//	@JsonIgnoreProperties
//	private MemberRoleKey memberRoleKey;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="member_id",referencedColumnName="id")
	@JsonBackReference
	private Member member;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", referencedColumnName="id")
	@JsonIgnore
	private Role role;
}
