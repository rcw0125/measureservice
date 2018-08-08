package com.talent.security.server.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.talent.core.service.BaseService;
import com.talent.security.server.model.ClientDetail;

@Repository
public interface ClientDetailRepository extends BaseService<ClientDetail, Long>{
	
	@Cacheable(value="ClientCache", key="#p0")
	@Query("from ClientDetail t where t.clientId = ?1")
	public ClientDetail findByClientId(String clientId);
}
