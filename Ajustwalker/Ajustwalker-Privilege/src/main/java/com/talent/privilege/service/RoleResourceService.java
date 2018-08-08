package com.talent.privilege.service;

import org.springframework.stereotype.Repository;
import com.talent.core.privilege.model.ResourceRole;
import com.talent.core.service.BaseService;

@Repository
public interface RoleResourceService extends BaseService<ResourceRole, Long>{
	
}
