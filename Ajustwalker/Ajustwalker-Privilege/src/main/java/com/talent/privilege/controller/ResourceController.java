package com.talent.privilege.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.QueryParams;
import com.talent.core.privilege.model.Resource;
import com.talent.core.privilege.model.ResourceRole;
import com.talent.core.privilege.model.ResourceUser;
import com.talent.core.privilege.model.UserRole;
import com.talent.core.privilege.service.UserRoleService;
import com.talent.privilege.service.ResourceService2;
import com.talent.privilege.service.RoleResourceService;
import com.talent.privilege.service.UserResourceService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResourceController extends BaseController {

	@Autowired
	private ResourceService2 resourceService;
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	@Autowired
	private UserResourceService userResourceService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping(value = "/resource")  
	public String init(ModelMap model) {
		return "resource";
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/list")  
	public List<Map<String,Object>> queryPage(String loadby,long loadid,ModelMap model) {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try{
			List<Resource> allResource = resourceService.findAllResourceOrded();
			if("INIT".equals(loadby)){
				for(Resource item : allResource){
					Map<String,Object> ri = new HashMap<String,Object>();
					ri.put("id", item.getId());
					ri.put("fid", item.getFid());
					ri.put("name", item.getResourcename());
					ri.put("type", "INIT"); 
					result.add(ri);
				}
			}else if("ROLE".equals(loadby)){
				ResourceRole rr = new ResourceRole();
				rr.setRoleid(loadid);
				QueryParams<ResourceRole> params = new QueryParams<ResourceRole>(rr);
				params.eq("roleid", false);
				List<ResourceRole> selectedResources = roleResourceService.query(params);
				for(Resource item : allResource){
					Map<String,Object> ri = new HashMap<String,Object>();
					ri.put("id", item.getId());
					ri.put("fid", item.getFid());
					ri.put("name", item.getResourcename());
					ri.put("type", "ROLE"); 
					for(ResourceRole selectedResource : selectedResources){
						if(item.getId() == selectedResource.getResourceid()){
							ri.put("checked",true); 
						}
					}
					result.add(ri);
				}
			}else if("USER".equals(loadby)){
				//查找用户自定义权限对象
				ResourceUser ru = new ResourceUser();
				ru.setUserid(loadid);
				QueryParams<ResourceUser> params = new QueryParams<ResourceUser>(ru);
				params.eq("userid", false);
				List<ResourceUser> selectedUserResources = userResourceService.query(params);
				
				//查找用户通过角色获取的权限对象
				List<ResourceRole> selectedRoleResources = new ArrayList<ResourceRole>();
				UserRole ur = new UserRole();
				ur.setUserid(loadid);
				QueryParams<UserRole> urparams = new QueryParams<UserRole>(ur);
				urparams.eq("userid", false);
				List<UserRole> urList = userRoleService.query(urparams);
				if(!urList.isEmpty()){
					List<Long> roleids = new ArrayList<Long>();
					for(UserRole item : urList){
						roleids.add(item.getRoleid());
					}
					ResourceRole rr = new ResourceRole();
					QueryParams<ResourceRole> rrparams = new QueryParams<ResourceRole>(rr);
					rrparams.in("roleid",roleids);
					selectedRoleResources = roleResourceService.query(rrparams);
				}
				
				for(Resource item : allResource){
					Map<String,Object> ri = new HashMap<String,Object>();
					ri.put("id", item.getId());
					ri.put("fid", item.getFid());
					ri.put("name", item.getResourcename());
					for(ResourceUser selectedUserResource : selectedUserResources){
						if(item.getId() == selectedUserResource.getResourceid()){
							ri.put("type", "USER");
							ri.put("checked",true); 
						}
					}
					for(ResourceRole selectedRoleResource : selectedRoleResources){
						if(item.getId() == selectedRoleResource.getResourceid()){
							ri.put("type", "ROLE");
							ri.put("checked",true); 
						}
					}
					result.add(ri);
				}
			}
		}catch (Exception e){
			
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/edit")  
	public Message addorEdit(Resource resource,ModelMap model) {
		Message msg = new Message();
		try{
			resourceService.insertOrUpdate(resource, true);
			msg.setTotal(resource.getId());
		}catch(PersistenceException ex){
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}catch(DuplicateKeyException e){
			msg.setSuccess(false);
			msg.setMsg("试图添加重复数据，操作失败！");
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/delete")
	public Message del(Resource resource,ModelMap model) {
		Message msg = new Message();
		try{
			resourceService.deleteByIds(resource);
		}catch(PersistenceException ce){
			if(ce.getCause() instanceof ConstraintViolationException){
				msg.setSuccess(false);
				msg.setMsg("删除失败，其它用户或角色使用该资源！");
			}else{
				msg.setSuccess(false);
				msg.setMsg("数据库操作失败！");
			}
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/form")
	public Resource loadForm(Resource resource,ModelMap model) {
		try{
			if(-1 != resource.getId() && -2 != resource.getId()){
				resource = resourceService.findOne(resource.getId());
			}else if(-2 == resource.getId()){
				resource.setResourcetype("menu");
				resource.setFid(0);
			}
			if(null == resource){
				resource = new Resource();
			}
		}catch (Exception e) {	
			resource = new Resource();
		}
		return resource; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/role", method = RequestMethod.POST)
	public Message resourcrole(@RequestBody ResourceRole ResourceRole,ModelMap model) {
		QueryParams<ResourceRole> qp = new QueryParams<ResourceRole>(ResourceRole);
		qp.eq("roleid", false);
		try {
			List<ResourceRole> allOldRoleResource = roleResourceService.query(qp);
			if(null != allOldRoleResource && !allOldRoleResource.isEmpty()){
				boolean oldsFound;
				List<Integer> newsFound = new ArrayList<Integer>();
				List<Map<String,String>> newResource = ResourceRole.getExparams();
				for(ResourceRole oldRR : allOldRoleResource){
					oldsFound = false;
					if(null != newResource){
						for(int i=0;i<newResource.size();i++){
							if(oldRR.getResourceid() == Long.parseLong(newResource.get(i).get("id"))){
								oldsFound = true;
								newsFound.add(i);
								break;
							}
						}
					}					
					if(!oldsFound){
						roleResourceService.delete(oldRR);
					}
				}
				if(null != newResource){
					for(int i=0;i<newResource.size();i++){
						if(!newsFound.contains(i)){
							ResourceRole nrr = new ResourceRole();
							nrr.setRoleid(ResourceRole.getRoleid());
							nrr.setResourceid(Long.parseLong(newResource.get(i).get("id").toString()));
							roleResourceService.insert(nrr, false);
						}
					}
				}
				roleResourceService.flush();
			}else{
				for(Map<String,String> item : ResourceRole.getExparams()){
					ResourceRole nrr = new ResourceRole();
					nrr.setRoleid(ResourceRole.getRoleid());
					nrr.setResourceid(Long.parseLong(item.get("id")));
					roleResourceService.insert(nrr, false);
				}
				roleResourceService.flush();
			}
			return new Message(true,"角色赋权成功！");
		} catch (Exception e) {
			return new Message(false,"角色赋权失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/user", method = RequestMethod.POST)
	public Message resourcuser(@RequestBody ResourceUser ResourceUser,ModelMap model) {
		QueryParams<ResourceUser> qp = new QueryParams<ResourceUser>(ResourceUser);
		qp.eq("userid", false);
		try {
			List<ResourceUser> allOldUserResource = userResourceService.query(qp);
			if(null != allOldUserResource && !allOldUserResource.isEmpty()){
				boolean oldsFound;
				List<Integer> newsFound = new ArrayList<Integer>();
				List<Map<String,String>> newResource = ResourceUser.getExparams();
				for(ResourceUser oldRU : allOldUserResource){
					oldsFound = false;
					if(null != newResource){
						for(int i=0;i<newResource.size();i++){
							if(oldRU.getResourceid() == Long.parseLong(newResource.get(i).get("id"))){
								oldsFound = true;
								newsFound.add(i);
								break;
							}
						}
					}
					if(!oldsFound){
						userResourceService.delete(oldRU);
					}
				}
				if(null != newResource){
					for(int i=0;i<newResource.size();i++){
						if(!newsFound.contains(i)){
							ResourceUser nru = new ResourceUser();
							nru.setUserid(ResourceUser.getUserid());
							nru.setResourceid(Long.parseLong(newResource.get(i).get("id").toString()));
							userResourceService.insert(nru, false);
						}
					}
				}
				userResourceService.flush();
			}else{
				for(Map<String,String> item : ResourceUser.getExparams()){
					ResourceUser nru = new ResourceUser();
					nru.setUserid(ResourceUser.getUserid());
					nru.setResourceid(Long.parseLong(item.get("id")));
					userResourceService.insert(nru, false);
				}
				userResourceService.flush();
			}
			return new Message(true,"用户赋权成功！");
		} catch (Exception e) {
			return new Message(false,"用户赋权失败！");
		}
	}
}
