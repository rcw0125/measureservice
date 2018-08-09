package com.talent.privilege.web;

import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.base.util.BaseController;
import com.talent.privilege.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController extends BaseController {

	@Autowired
	private PlatformDao platformDao;

	@ResponseBody
	@RequestMapping(value = "/role/queryPage.do")  
	public Message queryPage(Role role,String search,ModelMap model,PageModel pm) {
		
		Message msg = new Message();
		try{
			role.setRolename(search);
			pm = platformDao.queryPage(role, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		}catch (Exception e){	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/role/addoredit.do")  
	public Message addorEdit(Role role,ModelMap model) {
		
		Message msg = new Message();
		try{
			if(-1 == role.getId()){
				platformDao.getNewID(role);
				platformDao.insert(role);
			}else{
				platformDao.update(role);
			}
			msg.setTotal(role.getId());
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/role/del.do")  
	public Message del(Role role,String ids,ModelMap model) {
		
		Message msg = new Message();
		try{
			platformDao.remove(role, ids);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/role/loadform.do")  
	public Role loadForm(Role role,ModelMap model) {
		
		try{
			if(-1 != role.getId()){
				role = platformDao.get(role);
			}
			if(null == role){
				role = new Role();
			}
		}catch (Exception e) {	
			role = new Role();
		}
		return role; 
	}
}
