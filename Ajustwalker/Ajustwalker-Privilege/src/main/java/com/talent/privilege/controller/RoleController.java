package com.talent.privilege.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.model.QueryParams;
import com.talent.core.privilege.model.Role;
import com.talent.core.privilege.service.RoleService;
import com.talent.privilege.service.PrivilegeService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@RequestMapping(value = "/role")  
	public String init(ModelMap model) {
		return "role";
	}
	
	@ResponseBody
	@RequestMapping(value = "/role/list")  
	public Message queryPage(Role role,String searchText,ModelMap model,Pagination<Role> page) {
		try{
			QueryParams<Role> params = new QueryParams<Role>(role);
			if(null == searchText || searchText.length() == 0){
				params.like("rolecode", true);
				params.like("rolename", true);
			}else{
				List<String> p = new ArrayList<String>();
				p.add("rolecode");
				p.add("rolename");
				params.orLike(p, searchText);
			}
			return buildGridData(roleService.query(params,page));
		}catch (Exception e){
			return new Message(false,Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/role/edit")  
	public Message addorEdit(Role role,ModelMap model) {
		Message msg = new Message();
		try{
			roleService.insertOrUpdate(role, true);
			msg.setTotal(role.getId());
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
	@RequestMapping(value = "/role/delete")
	public Message del(Role role,ModelMap model) {
		Message msg = new Message();
		try{
			roleService.deleteByIds(role);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/role/form")
	public Role loadForm(Role role,ModelMap model) {
		try{
			if(-1 != role.getId()){
				role = roleService.findOne(role.getId());
			}
			if(null == role){
				role = new Role();
			}
		}catch (Exception e) {	
			role = new Role();
		}
		return role; 
	}
	
	/**
	 * 角色权限复制查询，查询出除了被复制者自己的所有角色
	 * @param role
	 * @param searchText
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/role/copyrights/list")  
	public Message queryCopyrightsPage(Role role,ModelMap model,Pagination<Role> page) {
		try{
			QueryParams<Role> params = new QueryParams<Role>(role);
			params.notEq("id", false);
			return buildGridData(roleService.query(params,page));
		}catch (Exception e){
			return new Message(false,Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/role/copyrights/edit")  
	public Message processCopyrights(long sourceRoleId,String targetRoleIds,ModelMap model) {
		try{
			privilegeService.processCopyRolerights(sourceRoleId, targetRoleIds);
			return new Message();
		}catch (Exception e){
			return new Message(false,Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
}
