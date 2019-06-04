package com.coco.rzido.config.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.coco.rzido.member.entity.Member;
import com.coco.rzido.member.repository.MemberRepository;
import com.coco.rzido.social.dto.TokenDTO;
import com.coco.rzido.social.entity.Social;
import com.coco.rzido.social.repository.SocialRepository;
import com.coco.rzido.socialLogin.ISocialAuth;
import com.coco.rzido.socialLogin.SocialLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	// @Autowired
	// private CustomUserDetailsService customUserDetailsService;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	SocialRepository socialRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		Map<String, String> details = (HashMap<String, String>)authentication.getDetails();
		String kind = details.get("kind");
		String password = (String) authentication.getCredentials();
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setKind(kind);
		tokenDTO.setToken(username);
		ISocialAuth create = SocialLogin.create(tokenDTO);
		String socialKey = create.userInfo().getSocialKey();
		Optional<Social> findSocial = socialRepository.findByKindAndSocialKey(kind,socialKey);
		if (!findSocial.isPresent()) {
			throw new BadCredentialsException("유저가 존재하지 않습니다.");
		}

			//회원이 아닌 경우에는 바로 가입하고 로그인 프로세스를 탄다.
			/*if(!memberRepository.findByKakaoKey(kakaoKey).isPresent()) {
				Member member = new Member();
				member.setMemberName(kakaoKey);
				member.setMemberPassword("카카오 소셜 로그인");
				member.setName(create.userInfo().getName());
				member.setKakaoKey(kakaoKey);
				Role role = new Role();
				role.setId(2L);
				member.setRoles(Arrays.asList(role));
				memberRepository.save(member);
			}*/
			


//		if(!StringUtils.equals(loadUserByUsername.getUsername(), username)) {
//			throw new BadCredentialsException("존재하지 않는 아이디입니다.");
//		}

		Optional<Member> member = findSocial.map(Social::getMember);
		SecurityMember securityMember = member.map(m -> new SecurityMember(m)).get();

		return new UsernamePasswordAuthenticationToken(securityMember, password, securityMember.getAuthorities());

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
