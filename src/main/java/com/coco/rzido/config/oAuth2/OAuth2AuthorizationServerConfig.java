package com.coco.rzido.config.oAuth2;

import java.util.Arrays;

import com.coco.rzido.config.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomAccessTokenConverter customAccessTokenConverter;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
	
	@Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(customAccessTokenConverter);
        converter.setSigningKey("rzido2019.key");
        return converter;
    }
	
	@Bean
	@Primary
	public DefaultTokenServices tokenService() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
    }
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		endpoints.userDetailsService(customUserDetailsService);
		endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain).authenticationManager(authenticationManager);
	}
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            clients.inMemory()
                            .withClient("rzido")
							.secret(passwordEncoder.encode("rzido2019"))
//                            .secret("$2a$10$oXhcs6qujNbFA5yXauupSuWLQpMjVbAskvPbMvcUzurpsdIuSXs7m")
                            .authorizedGrantTypes("authorization_code", "refresh_token",
                            		"password").scopes("openid");
    }
	
	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer)
                    throws Exception {
            oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
    }
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
	    return new CustomTokenEnhancer();
	}
	
}
