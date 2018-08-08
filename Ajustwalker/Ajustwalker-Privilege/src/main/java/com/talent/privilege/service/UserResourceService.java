package com.talent.privilege.service;

import org.springframework.stereotype.Repository;
import com.talent.core.privilege.model.ResourceUser;
import com.talent.core.service.BaseService;

@Repository
public interface UserResourceService extends BaseService<ResourceUser, Long>{
	
}
