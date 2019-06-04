package com.coco.rzido.auth.dto;

import java.util.Collection;

import com.coco.rzido.member.entity.Member;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class AuthDTO {
	private Long memberId;
	private String memberName;
	private boolean admin;
	private Collection<GrantedAuthority> authorities;
	private boolean authenticated = false;
	
	/**
	 * @param id
	 * @return
	 * @설명 어드민 권한을 가지고 있는지, 현재로그인한 멤버와 저장된 컨텐츠가 일치하는지 확인
	 */
	public boolean permission(Long id) {
		if(admin) return admin; // 어드민권한을 가지고 있으면 무조건 퍼미션이 true임
		
		if(memberId == null || memberId.equals(0L)) {
			return false;
		}else if(id == null || id.equals(0L)) {
			return false;
		}else if(memberId.equals(id)) {
			return true;
		}else {
			return false;
		}
	}
	
	public Member getMember(){
		Member member = new Member();
		member.setId(memberId);
		return member;
	}

	public void authenticated(boolean authenticated){
		this.authenticated = authenticated;
	}
	
	public boolean isAuthenticated(){
		return authenticated;
	}
	
	
}
