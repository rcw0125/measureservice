package com.talent.security.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import com.talent.core.model.BaseModel;

import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;

@Entity
@Table(name="S_OAUTH2_ACCESS_TOKEN_T",uniqueConstraints={@UniqueConstraint(columnNames={"tokenId"})})
public class OAuth2AuthenticationAccessToken extends BaseModel{

	private static final long serialVersionUID = -222780359368189601L;

	private String tokenId;
    
    private String authenticationId;
    
    private String userName;
    
    private String clientId;
    
    private String refreshToken;
    
    @IgnoreSizeOf
    @Transient
    private OAuth2AccessToken oAuth2AccessToken;
    
    @IgnoreSizeOf
    @Transient
    private OAuth2Authentication authentication;
    
    public OAuth2AuthenticationAccessToken() {
    	
    }

    public OAuth2AuthenticationAccessToken(final OAuth2AccessToken oAuth2AccessToken, final OAuth2Authentication authentication, final String authenticationId) {
        this.tokenId = oAuth2AccessToken.getValue();
        this.oAuth2AccessToken = oAuth2AccessToken;
        this.authenticationId = authenticationId;
        this.userName = authentication.getName();
        this.clientId = authentication.getOAuth2Request().getClientId();
        this.authentication = authentication;
        this.refreshToken = oAuth2AccessToken.getRefreshToken().getValue();
    }

    public String getTokenId() {
        return tokenId;
    }

    public OAuth2AccessToken getoAuth2AccessToken() {
        return oAuth2AccessToken;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public String getUserName() {
        return userName;
    }

    public String getClientId() {
        return clientId;
    }

    public OAuth2Authentication getAuthentication() {
        return authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public void setoAuth2AccessToken(OAuth2AccessToken oAuth2AccessToken) {
		this.oAuth2AccessToken = oAuth2AccessToken;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setAuthentication(OAuth2Authentication authentication) {
		this.authentication = authentication;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}