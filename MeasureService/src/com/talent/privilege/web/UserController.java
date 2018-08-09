package com.talent.privilege.web;

import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.base.util.BaseController;
import com.talent.base.util.PrivilegeUtil;
import com.talent.privilege.dao.PrivilegeDao;
import com.talent.privilege.model.Role;
import com.talent.privilege.model.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController {

	@Autowired
	private PlatformDao platformDao;
	
	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Autowired
	private PrivilegeUtil privilegeUtil;

	@ResponseBody
	@RequestMapping(value = "/user/queryPage.do")  
	public Message queryPage(User user,String search,ModelMap model,PageModel pm) {
		
		Message msg = new Message();
		try{
			user.setUsername(search);
			pm = platformDao.queryPage(user, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		}catch (Exception e){	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/addoredit.do")  
	public Message addorEdit(User user,ModelMap model) {
		
		Message msg = new Message();
		try{
			if(-1 == user.getId()){
				platformDao.getNewID(user);
				user.setUsercode(user.getUsercode().toUpperCase());
				user.setPassword(privilegeUtil.md5(user.getUsercode() + user.getPassword()));
				platformDao.insert(user);
			}else{
				if(user.getPassword().length() != 32){
					user.setPassword(privilegeUtil.md5(user.getUsercode() + user.getPassword()));
				}				
				platformDao.update(user);
			}
			msg.setTotal(user.getId());
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/del.do")  
	public Message del(User user,String ids,ModelMap model) {
		
		Message msg = new Message();
		try{
			platformDao.remove(user, ids);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/loadform.do")  
	public User loadForm(User user,ModelMap model) {
		
		try{
			if(-1 != user.getId()){
				user = platformDao.get(user);
			}
			if(null == user){
				user = new User();
			}
		}catch (Exception e) {	
			user = new User();
		}
		return user; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/userole/queryList.do")  
	public List<Role> queryList(User user,String search,ModelMap model) {
		
		List<Role> list = null;
		try{
			list = privilegeDao.selectUserRole(user);
		}catch (Exception e){	
			list = new ArrayList<Role>();
		}
		return list; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/userole/setrole.do")
	public Message setRole(User user,String optr,ModelMap model) {
		
		Message msg = new Message();
		try{
			if("添加".equals(optr)){
				privilegeDao.insertUserRole(user);
			}else{
				privilegeDao.deleteUserRole(user);
			}
		}catch (Exception e){	
			
		}
		return msg; 
	}
}
