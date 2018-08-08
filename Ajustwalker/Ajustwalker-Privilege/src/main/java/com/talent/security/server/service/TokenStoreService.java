package com.talent.security.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import com.talent.core.cache.CacheService;
import com.talent.security.server.model.OAuth2AuthenticationAccessToken;
import com.talent.security.server.model.OAuth2AuthenticationRefreshToken;

public class TokenStoreService implements TokenStore {

    @Autowired
    private CacheService cacheService;
    
    private final AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String tokenId) {
        OAuth2AuthenticationAccessToken token = (OAuth2AuthenticationAccessToken) cacheService.getCache("TokenCache").get("AccessToken" + tokenId);
        return null == token ? null : token.getAuthentication();
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        OAuth2AuthenticationAccessToken oAuth2AuthenticationAccessToken = new OAuth2AuthenticationAccessToken(token,
                authentication, authenticationKeyGenerator.extractKey(authentication));
        try {
        	cacheService.getCache("TokenCache").put("AccessToken" + token.getValue(),oAuth2AuthenticationAccessToken);
        	cacheService.getCache("TokenCache").put("AccessToken" + token.getRefreshToken(),oAuth2AuthenticationAccessToken);
        	cacheService.getCache("TokenCache").put("AccessToken" + oAuth2AuthenticationAccessToken.getAuthenticationId(),oAuth2AuthenticationAccessToken);
        	cacheService.getCache("TokenCache").put("AccessToken" + oAuth2AuthenticationAccessToken.getClientId() + "@@" + oAuth2AuthenticationAccessToken.getAuthenticationId(),oAuth2AuthenticationAccessToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenId) {
        OAuth2AuthenticationAccessToken token = (OAuth2AuthenticationAccessToken) cacheService.getCache("TokenCache").get("AccessToken" + tokenId);
        if (null == token) {
            throw new InvalidTokenException("令牌不正确，请联系管理员或重新登录！");
        }
        return token.getoAuth2AccessToken();
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken accessToken) {
        OAuth2AuthenticationAccessToken token = (OAuth2AuthenticationAccessToken) cacheService.getCache("TokenCache").get("AccessToken" + accessToken.getValue());
        if (token != null) {
        	cacheService.getCache("TokenCache").remove("AccessToken" + accessToken.getValue());
        }
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        try {
			cacheService.getCache("TokenCache").put("RefreshToken" + refreshToken.getValue(), new OAuth2AuthenticationRefreshToken(refreshToken, authentication));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private OAuth2AuthenticationRefreshToken findRefreshTokenByTokenId(String tokenId){
    	return (OAuth2AuthenticationRefreshToken)cacheService.getCache("TokenCache").get("RefreshToken" + tokenId);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String accessToken) {
        return findRefreshTokenByTokenId(accessToken).getoAuth2RefreshToken();
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return findRefreshTokenByTokenId(token.getValue()).getAuthentication();
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken refreshToken) {
    	cacheService.getCache("TokenCache").remove("RefreshToken" + refreshToken.getValue());
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        OAuth2AuthenticationAccessToken accessToken = (OAuth2AuthenticationAccessToken)cacheService.getCache("TokenCache").get("AccessToken" + refreshToken.getValue());
        cacheService.getCache("TokenCache").remove("AccessToken" + refreshToken.getValue());
        cacheService.getCache("TokenCache").remove("AccessToken" + accessToken.getTokenId());
        cacheService.getCache("TokenCache").remove("AccessToken" + accessToken.getAuthenticationId());
        cacheService.getCache("TokenCache").remove("AccessToken" + accessToken.getClientId() + "@@" + accessToken.getAuthenticationId());
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String authenticationId = authenticationKeyGenerator.extractKey(authentication);
        if (null == authenticationId) {
            return null;
        }
        
        OAuth2AuthenticationAccessToken accessToken = (OAuth2AuthenticationAccessToken)cacheService.getCache("TokenCache").get("AccessToken" + authenticationId);
        if(null == accessToken){
        	return null;
        }else{
        	return accessToken.getoAuth2AccessToken();
        }
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
    	
		try {
			OAuth2AuthenticationAccessToken oat = new OAuth2AuthenticationAccessToken();
	        List<OAuth2AuthenticationAccessToken> accessTokens = cacheService.getCache("TokenCache").list(oat, "AccessToken" + clientId);
			return extractAccessTokens(accessTokens);
		} catch (Exception e) {
			return null;
		}
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        try {
        	OAuth2AuthenticationAccessToken oat = new OAuth2AuthenticationAccessToken();
        	List<OAuth2AuthenticationAccessToken> accessTokensByUserName = new ArrayList<OAuth2AuthenticationAccessToken>();
	        List<OAuth2AuthenticationAccessToken> accessTokens = cacheService.getCache("TokenCache").list(oat, "AccessToken" + clientId);
	        for(OAuth2AuthenticationAccessToken temp : accessTokens){
	        	if(userName.equals(temp.getUserName())){
	        		accessTokensByUserName.add(temp);
	        	}
	        }
			return extractAccessTokens(accessTokensByUserName);
		} catch (Exception e) {
			return null;
		}
    }

    private Collection<OAuth2AccessToken> extractAccessTokens(List<OAuth2AuthenticationAccessToken> tokens) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<>();
        tokens.stream().forEach(token -> {
            accessTokens.add(token.getoAuth2AccessToken());
        });
        return accessTokens;
    }
}