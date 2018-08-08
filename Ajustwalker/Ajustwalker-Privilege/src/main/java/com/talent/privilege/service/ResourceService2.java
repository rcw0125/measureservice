package com.talent.privilege.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.talent.core.service.BaseService;
import com.talent.core.privilege.model.Resource;

@Repository
public interface ResourceService2 extends BaseService<Resource, Long>{
	@Query("from Resource t where t.isdisplay = ?1 and t.resourcetype='menu' order by (t.fid + t.sn) asc")
	public List<Resource> findResourceByDisplay(int isdisplay);
	
	@Query("from Resource t order by (t.fid + t.sn) asc")
	public List<Resource> findAllResourceOrded();
}
