package com.talent.security.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import com.talent.core.model.BaseModel;

import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;

@Entity
@Table(name="S_OAUTH2_REFRESH_TOKEN_T",uniqueConstraints={@UniqueConstraint(columnNames={"tokenId"})})
public class OAuth2AuthenticationRefreshToken extends BaseModel{

	private static final long serialVersionUID = 6046647179277858699L;

	private String tokenId;
    
	@IgnoreSizeOf
	@Transient
    private OAuth2RefreshToken oAuth2RefreshToken;
    
	@IgnoreSizeOf
	@Transient
    private OAuth2Authentication authentication;
	
	public OAuth2AuthenticationRefreshToken(){
		
	}

    public OAuth2AuthenticationRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication authentication) {
        this.oAuth2RefreshToken = oAuth2RefreshToken;
        this.authentication = authentication;
        this.tokenId = oAuth2RefreshToken.getValue();
    }

    public String getTokenId() {
        return tokenId;
    }

    public OAuth2RefreshToken getoAuth2RefreshToken() {
        return oAuth2RefreshToken;
    }

    public OAuth2Authentication getAuthentication() {
        return authentication;
    }

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public void setoAuth2RefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
		this.oAuth2RefreshToken = oAuth2RefreshToken;
	}

	public void setAuthentication(OAuth2Authentication authentication) {
		this.authentication = authentication;
	}
}