package com.talent.security.server.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserOauthService extends UserDetailsService {
	
	boolean isExistedUsername(String username);
	
}