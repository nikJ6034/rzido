package com.coco.rzido.member.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.coco.rzido.common.entity.BaseEntity;
import com.coco.rzido.role.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member extends BaseEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String memberName;
	@Column
	private String name;
	
	@Column
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String memberPassword;

	@Column
	private String email;

	@Column String mobile;
	
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	@JoinColumn(name="member_id")
//	@JsonManagedReference
//	private List<MemberRole> roles;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="member_role",
		joinColumns = @JoinColumn(name="member_id"),
		inverseJoinColumns = @JoinColumn(name="role_id")
	)
	private List<Role> roles;
}
