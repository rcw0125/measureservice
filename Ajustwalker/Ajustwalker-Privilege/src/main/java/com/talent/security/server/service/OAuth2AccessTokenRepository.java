package com.talent.security.server.service;

import org.springframework.stereotype.Repository;
import com.talent.core.service.BaseService;
import com.talent.security.server.model.OAuth2AuthenticationAccessToken;

@Repository
public interface OAuth2AccessTokenRepository extends BaseService<OAuth2AuthenticationAccessToken, Long>{

}
