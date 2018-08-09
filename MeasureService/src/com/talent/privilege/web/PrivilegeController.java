package com.talent.privilege.web;

import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.base.util.PrivilegeUtil;
import com.talent.privilege.dao.PrivilegeDao;
import com.talent.privilege.model.Resource;
import com.talent.privilege.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrivilegeController extends BaseController {

	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Autowired
	private PrivilegeUtil privilegeUtil;
	
	@ResponseBody
	@RequestMapping("/measure/login.do")
	public Message clientLogin(User user, ModelMap model) throws Exception {

		Message msg = new Message();
		Map<String, String> userinfo = new HashMap<String, String>();
		List<Resource> treeList = new ArrayList<Resource>();
		User u = privilegeDao.getLoginUser(user);
		if (null != u) {
			userinfo.put("usercode", u.getUsercode());
			userinfo.put("username", u.getUsername());
			treeList = privilegeDao.queryResourcesByUsercode(user);
			msg.setMore(userinfo);
		} else {
			msg.setSuccess(false);
			msg.setMsg("登录失败，请检查用户名和密码！");
		}		
		msg.setData(treeList);
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/privilege/login.do")
	public Message privilegeLogin(User user, ModelMap model,HttpServletRequest request) throws Exception {
		
		Message msg = new Message();
		Map<String, String> userinfo = new HashMap<String, String>();
		user.setUsercode(user.getUsercode().toUpperCase());
		user.setPassword(privilegeUtil.md5(user.getUsercode() + user.getPassword()));
		User u = privilegeDao.getLoginUser(user);
		if (null != u){
			userinfo.put("usercode", u.getUsercode());
			userinfo.put("username", u.getUsername());
			msg.setMore(userinfo);
		} else {
			msg.setSuccess(false);
			msg.setMsg("登录失败，请检查用户名和密码！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/privilege/getAllMeasureUser.do")
	public List<User> getAllMeasureUser() throws Exception {		
		return privilegeDao.getAllUser();
	}
	/**
	 * 坐席密码修改服务
	 */
	@ResponseBody
	@RequestMapping("/privilege/updatePassword.do")
	public Message updatePassword(User user) throws Exception {
		Message msg = new Message();
		user.setUsercode(user.getUsercode().toUpperCase());
		user.setRepassword(privilegeUtil.md5(user.getUsercode().toUpperCase() + user.getRepassword()));
		User u = privilegeDao.queryPassword(user);
		if(null == u){
			msg.setSuccess(false);
			msg.setMsg("用户名输入错误！");
		}else{
			try {
				if (user.getRepassword().equals(u.getPassword())) {
					user.setPassword(privilegeUtil.md5(user.getUsercode() + user.getPassword()));
					if(privilegeDao.updatePassword(user)>0){
						msg.setSuccess(true);
						msg.setMsg("密码修改成功！");
					}
				} else {
					msg.setSuccess(false);
					msg.setMsg("原密码填写不正确！");
				}
			}catch (Exception e){
				
			}
		}
		return msg;
	}
}
