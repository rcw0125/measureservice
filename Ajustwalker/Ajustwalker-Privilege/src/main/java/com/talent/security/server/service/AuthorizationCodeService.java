package com.talent.security.server.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

public class AuthorizationCodeService extends RandomValueAuthorizationCodeServices {

	@Override
	@Cacheable(value = "authorizationCodeCache", key = "#code")
	protected void store(String code, OAuth2Authentication authentication) {
		//super.store(code, authentication);
	}

	@Override
	@CacheEvict(value = "authorizationCodeCache", key = "#code")
	public OAuth2Authentication remove(String code) {
		OAuth2Authentication authentication = null;
		return authentication;
	}
}
