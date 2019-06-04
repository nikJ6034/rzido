package com.coco.rzido.config.security;

import java.util.Optional;

import com.coco.rzido.member.entity.Member;
import com.coco.rzido.member.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> member = memberRepository.findByMemberName(username);
		return member.map(m-> new SecurityMember(m)).get();
		
	}
}
