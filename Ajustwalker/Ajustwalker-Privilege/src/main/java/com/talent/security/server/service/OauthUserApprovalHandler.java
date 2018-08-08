package com.talent.security.server.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

public class OauthUserApprovalHandler extends TokenStoreUserApprovalHandler {

	private ClientDetailService clientDetailService;

	public OauthUserApprovalHandler() {
		
	}

	/**
	 * 这儿扩展了默认的逻辑, 若 OauthClientDetails 中的 trusted 字段为true, 将会自动跳过 授权流程
	 *
	 * @param authorizationRequest
	 *            AuthorizationRequest
	 * @param userAuthentication
	 *            Authentication
	 * @return True is approved
	 */
	public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		if (super.isApproved(authorizationRequest, userAuthentication)) {
			return true;
		}
		if (!userAuthentication.isAuthenticated()) {
			return false;
		}

		ClientDetails clientDetails = clientDetailService.loadClientByClientId(authorizationRequest.getClientId());
		return clientDetails != null;
	}

	public void setClientDetailService(ClientDetailService clientDetailService) {
		this.clientDetailService = clientDetailService;
	}
}
