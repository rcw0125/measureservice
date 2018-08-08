package com.talent.privilege.controller;

import com.alibaba.druid.util.StringUtils;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.model.QueryParams;
import com.talent.core.privilege.model.Role;
import com.talent.core.privilege.model.User;
import com.talent.core.privilege.model.UserRole;
import com.talent.core.privilege.service.RoleService;
import com.talent.core.privilege.service.UserRoleService;
import com.talent.privilege.service.PrivilegeService;
import com.talent.privilege.service.UsersService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersController extends BaseController {

	@Autowired
	private UsersService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@RequestMapping(value = "/user")  
	public String init(ModelMap model) {
		return "user";
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/list")  
	public Message queryPage(User user,String searchText,ModelMap model,Pagination<User> page) {
		try{
			QueryParams<User> params = new QueryParams<User>(user);
			if(null == searchText || StringUtils.isEmpty(searchText)){
				params.like("username",true);
				params.like("displayname",true);
			}else{
				List<String> p = new ArrayList<String>();
				p.add("username");
				p.add("displayname");
				params.orLike(p, searchText);
			}
			return buildGridData(userService.query(params,page));
		}catch (Exception e){
			return new Message(false,Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/edit")  
	public Message addorEdit(User user,ModelMap model) {
		Message msg = new Message();
		try{
			if(!user.getPassword().equals(user.getRepassword())){
				msg.setSuccess(false);
				msg.setMsg("两次输入的密码不一致，请重试！");
			}else{
				if(!user.getPrepassword().equals(user.getPassword())){
					user.setPassword(passwordEncoder.encode(user.getPassword()));
				}
				userService.insertOrUpdate(user, true);
			}
			msg.setTotal(user.getId());
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
	@RequestMapping(value = "/user/repassword")
	public Message repassword(String repassword,String password,ModelMap model) {
		try{
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (passwordEncoder.matches(repassword, u.getPassword())) {
				userService.repassword(u.getUsername(), passwordEncoder.encode(password));
				return new Message();
			}else{
				return new Message(false,"原密码输入错误！");
			}
		}catch(Exception e){
			return new Message(false,"修改密码失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/delete")
	public Message del(User user,ModelMap model) {
		Message msg = new Message();
		try{
			userService.deleteByIds(user);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/form")
	public User loadForm(User user,ModelMap model) {
		try{
			if(-1 != user.getId()){
				user = userService.findOne(user.getId());
			}
			if(null == user){
				user = new User();
			}else{
				user.setRepassword(user.getPassword());
				user.setPrepassword(user.getPassword());
			}
		}catch (Exception e) {	
			user = new User();
		}
		return user; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/role")  
	public Message userRole(UserRole userRole,String searchText,ModelMap model) {
		try{
			Role r = new Role();
			QueryParams<Role> roleparams = new QueryParams<Role>(r);
			List<String> p = new ArrayList<String>();
			p.add("rolecode");
			p.add("rolename");
			roleparams.orLike(p, searchText);
			List<Role> allRoles = roleService.query(roleparams);
			
			QueryParams<UserRole> params = new QueryParams<UserRole>(userRole);
			params.eq("userid", false);
			List<UserRole> userRoles = userRoleService.query(params);
			
			for(UserRole ur : userRoles){
				for(Role role : allRoles){
					if(role.getId() == ur.getRoleid()){
						role.setSelected(ur.getId());
					}
				}
			}
			
			return buildGridData(allRoles);
		}catch (Exception e){	
			return new Message(false,"操作失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/role/set")  
	public Message userRoleSet(UserRole userRole,long userroleid,String optr,ModelMap model) {
		try{
			if("ADD".equals(optr)){
				userRoleService.insert(userRole, true);
			}else{
				userRoleService.delete(userroleid);
			}
			return new Message(true,"角色设置成功");
		}catch (Exception e){	
			return new Message(false,"操作失败！");
		}
	}
	
	/**
	 * 用户权限复制查询，查询出非被复制用户的其它用户
	 * @param user
	 * @param searchText
	 * @param model
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/copyrights/list")  
	public Message queryCopyrightsPage(User user,ModelMap model,Pagination<User> page) {
		try{
			QueryParams<User> params = new QueryParams<User>(user);
			params.notEq("id", false);
			return buildGridData(userService.query(params,page));
		}catch (Exception e){
			return new Message(false,Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/copyrights/edit")  
	public Message processCopyrights(long sourceUserId,String targetUserIds,ModelMap model) {
		try{
			privilegeService.processCopyUserrights(sourceUserId, targetUserIds);
			return new Message();
		}catch (Exception e){
			return new Message(false,Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
}
