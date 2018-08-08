package com.talent.security.server.service;

import org.springframework.stereotype.Repository;
import com.talent.core.service.BaseService;
import com.talent.security.server.model.OAuth2AuthenticationRefreshToken;

@Repository
public interface OAuth2RefreshTokenRepository extends BaseService<OAuth2AuthenticationRefreshToken, Long>{
	
}