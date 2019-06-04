package com.coco.rzido.member.service;

import java.util.Arrays;

import com.coco.rzido.member.dto.SignupDTO;
import com.coco.rzido.member.entity.Member;
import com.coco.rzido.member.repository.MemberRepository;
import com.coco.rzido.role.entity.Role;
import com.coco.rzido.social.dto.SocialDTO;
import com.coco.rzido.social.dto.TokenDTO;
import com.coco.rzido.social.entity.Social;
import com.coco.rzido.social.repository.SocialRepository;
import com.coco.rzido.socialLogin.ISocialAuth;
import com.coco.rzido.socialLogin.SocialLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	SocialRepository socialRepository;
	
	public Member insertMember(SignupDTO signupDTO) {
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setToken(signupDTO.getToken());
		tokenDTO.setKind(signupDTO.getKind());
		Member member = new Member();


		ISocialAuth create = SocialLogin.create(tokenDTO);
		SocialDTO socialDTO = create.userInfo();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setMemberPassword(passwordEncoder.encode("ieumschool"));
		Role role = new Role();
		role.setId(2L); //일반 회원
		member.setRoles(Arrays.asList(role));
		member.setMemberName(signupDTO.getMemberName());
		member.setMobile(signupDTO.getMobile());
		member.setEmail(signupDTO.getEmail());
		member.setName(socialDTO.getName());
		Member save = memberRepository.save(member);

		Social social = new Social();
		social.setKind(tokenDTO.getKind());
		social.setSocialKey(socialDTO.getSocialKey());
		social.setMember(save);
		socialRepository.save(social);

		return save;
	}
	
	public Long existsMember(String memberName){
		return memberRepository.existsByMemberName(memberName);
	}
}
