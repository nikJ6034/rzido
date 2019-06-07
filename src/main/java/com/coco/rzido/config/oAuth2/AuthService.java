package com.coco.rzido.config.oAuth2;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import com.coco.rzido.auth.dto.AuthDTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	public AuthDTO getAuth() {
		Optional<SecurityContext> context = Optional.ofNullable(SecurityContextHolder.getContext());
		return context.map(sc -> {
			if(sc.getAuthentication() instanceof OAuth2Authentication) {
				return (OAuth2Authentication)sc.getAuthentication();
			}else {
				return null;
			}
			
		})
		.map(jwt->{
			AuthDTO authDTO = new AuthDTO();
			OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)jwt.getDetails();
			@SuppressWarnings("unchecked")
			Map<String, Object> decodedDetails = (LinkedHashMap<String, Object>)details.getDecodedDetails();
			String id = decodedDetails.get("id").toString();
			String name = decodedDetails.get("name").toString();
			Collection<GrantedAuthority> authorities = jwt.getAuthorities();
			authDTO.setAuthorities(authorities);
			authDTO.setAdmin(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
			authDTO.setMemberId(Long.parseLong(id));
			authDTO.setMemberName(jwt.getName());
			authDTO.setName(name);
			authDTO.authenticated(jwt.isAuthenticated());
			return authDTO;
		}).orElse(new AuthDTO());
	}
	
}
