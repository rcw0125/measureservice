package com.talent.security.server.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import com.talent.security.server.model.ClientDetail;

@Service
public class ClientDetailService implements ClientDetailsService, ClientRegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private ClientDetailRepository clientDetailRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetail clientDetails = clientDetailRepository.findByClientId(clientId);
        if (null == clientDetails) {
            throw new ClientRegistrationException("没有找到ID为：'" + clientId + "'的客户端！");
        }
        return getClientFromClientDetails(clientDetails);
    }

    @Override
    public void addClientDetails(ClientDetails cd) throws ClientAlreadyExistsException {
    	ClientDetail clientDetails = clientDetailRepository.findByClientId(cd.getClientId());
    	if (null == clientDetails) {
    		clientDetails = getClientDetailsFromClient(-1,cd);
    	}else{
    		clientDetails = getClientDetailsFromClient(clientDetails.getId(),cd);
    	}
        try {
			clientDetailRepository.insertOrUpdate(clientDetails,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void updateClientDetails(ClientDetails cd) throws NoSuchClientException {
        ClientDetail clientDetails = clientDetailRepository.findByClientId(cd.getClientId());
        if (null == clientDetails) {
            throw new NoSuchClientException("没有找到ID为：'" + cd.getClientId() + "'的客户端！");
        }
        clientDetails = getClientDetailsFromClient(clientDetails.getId(),cd);
        try {
			clientDetailRepository.insertOrUpdate(clientDetails,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        ClientDetail clientDetails = clientDetailRepository.findByClientId(clientId);
        if (null == clientDetails) {
            throw new NoSuchClientException("没有找到ID为：'" + clientId + "'的客户端！");
        }
        clientDetails.setClientSecret(passwordEncoder.encode(secret));
        try {
			clientDetailRepository.insertOrUpdate(clientDetails,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        ClientDetail clientDetails = clientDetailRepository.findByClientId(clientId);
        if (null == clientDetails) {
            throw new NoSuchClientException("没有找到ID为：'" + clientId + "'的客户端！");
        }
        clientDetailRepository.delete(clientDetails);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List listClientDetails() {
        List<ClientDetail> mdbcds = clientDetailRepository.findAll();
        return getClientsFromMongoDBClientDetails(mdbcds);
    }

    private List<BaseClientDetails> getClientsFromMongoDBClientDetails(List<ClientDetail> clientDetails) {
        List<BaseClientDetails> bcds = new LinkedList<>();
        if (clientDetails != null && !clientDetails.isEmpty()) {
            clientDetails.stream().forEach(mdbcd -> {
                bcds.add(getClientFromClientDetails(mdbcd));
            });
        }
        return bcds;
    }

    private BaseClientDetails getClientFromClientDetails(ClientDetail clientDetails) {
        BaseClientDetails bc = new BaseClientDetails();
        bc.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds());
        bc.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes());
        bc.setClientId(clientDetails.getClientId());
        bc.setClientSecret(clientDetails.getClientSecret());
        bc.setRefreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds());
        bc.setRegisteredRedirectUri(clientDetails.getRegisteredRedirectUri());
        bc.setResourceIds(clientDetails.getResourceIds());
        bc.setScope(clientDetails.getScope());
        return bc;
    }

    private ClientDetail getClientDetailsFromClient(long id,ClientDetails cd) {
        ClientDetail clientDetails = new ClientDetail();
        clientDetails.setAccessTokenValiditySeconds(cd.getAccessTokenValiditySeconds());
        clientDetails.setAdditionalInformation(cd.getAdditionalInformation());
        clientDetails.setAuthorizedGrantTypes(cd.getAuthorizedGrantTypes());
        clientDetails.setClientId(cd.getClientId());
        clientDetails.setClientSecret(cd.getClientSecret());
        clientDetails.setRefreshTokenValiditySeconds(cd.getRefreshTokenValiditySeconds());
        clientDetails.setRegisteredRedirectUri(cd.getRegisteredRedirectUri());
        clientDetails.setResourceIds(cd.getResourceIds());
        clientDetails.setScope(cd.getScope());
        clientDetails.setScoped(cd.isScoped());
        clientDetails.setSecretRequired(cd.isSecretRequired());
        clientDetails.setId(id);
        return clientDetails;
    }

    public ClientDetail save(ClientDetail authClient) {
    	authClient = clientDetailRepository.save(authClient);
    	clientDetailRepository.flush();
        return authClient;
    }

    public void deleteAll() {
        clientDetailRepository.deleteAll();
    }
}
