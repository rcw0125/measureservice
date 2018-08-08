package com.talent.security.server.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import com.talent.core.model.BaseModel;

@Entity
@Table(name="S_CLIENTDETAIL_T",uniqueConstraints={@UniqueConstraint(columnNames={"clientId"})})
public class ClientDetail extends BaseModel{

	private static final long serialVersionUID = 1853769919165138057L;

	private String clientId;
	
	private boolean secretRequired = true;
	
	private String clientSecret;
	
	private boolean scoped = true;
	
	private int accessTokenValiditySeconds = 4500;
	
	private int refreshTokenValiditySeconds = 4500;
	
	private boolean autoApprove;
	
	@Transient
	private Set<String> resourceIds = new HashSet<>(Arrays.asList("rest_api","sso-auth"));
	
	@Transient
	private Set<String> scope = new HashSet<>(Arrays.asList("trust", "read", "write"));
	
	@Transient
	private Set<String> authorizedGrantTypes = new HashSet<>(Arrays.asList("client_credentials", "authorization_code", "implicit", "password", "refresh_token"));
	
	@Transient
	private Set<String> registeredRedirectUri;
	
	@Transient
	private Collection<String> authorities = new HashSet<>(Arrays.asList("trust", "read", "write"));
	
	@Transient
	private Map<String, Object> additionalInformation;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Set<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public boolean isSecretRequired() {
		return secretRequired;
	}

	public Set<String> getScope() {
		return scope;
	}

	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	public void setSecretRequired(boolean secretRequired) {
		this.secretRequired = secretRequired;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public boolean isScoped() {
		return scoped;
	}

	public void setScoped(boolean scoped) {
		this.scoped = scoped;
	}

	public Set<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public Set<String> getRegisteredRedirectUri() {
		return registeredRedirectUri;
	}

	public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
		this.registeredRedirectUri = registeredRedirectUri;
	}

	public Collection<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<String> authorities) {
		this.authorities = authorities;
	}

	public boolean isAutoApprove() {
		return autoApprove;
	}

	public void setAutoApprove(boolean autoApprove) {
		this.autoApprove = autoApprove;
	}

	public Map<String, Object> getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(Map<String, Object> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public int getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public int getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}
}