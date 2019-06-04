package com.coco.rzido.config.oAuth2;

import java.util.HashMap;
import java.util.Map;

import com.coco.rzido.config.security.SecurityMember;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer{
	@Override
    public OAuth2AccessToken enhance( OAuth2AccessToken accessToken,  OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        SecurityMember sm = (SecurityMember)authentication.getUserAuthentication().getPrincipal();
        additionalInfo.put("id", sm.getId());
        additionalInfo.put("name", sm.getName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(
          additionalInfo);
        return accessToken;
    }
}
