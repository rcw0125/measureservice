package com.talent.measure.web;

import com.talent.base.dao.PlatformDao;
import com.talent.base.util.BaseController;
import com.talent.base.util.CacheUtil;
import com.talent.measure.model.BaseConfig;
import com.talent.measure.model.Equipment;
import com.talent.privilege.dao.PrivilegeDao;
import com.talent.privilege.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController extends BaseController{
	
	@Autowired
	private PlatformDao platformDao;
	
	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Autowired
	private CacheUtil cacheUtil;
	
	@RequestMapping(value = "/")  
	public String index(@CookieValue(value="usercode",defaultValue="")String usercode,@CookieValue(value="username",defaultValue="")String username,ModelMap model,HttpServletRequest request) {
		if(!"".equals(usercode) && !"null".equals(usercode)){
			User u = new User();
			u.setUsercode(usercode);
			model.addAttribute("usermenus",privilegeDao.queryMeasureMenuStrByUsercode(u,request.getContextPath()));
			model.addAttribute("username",username);
		}else{
			model.addAttribute("usermenus","");
			model.addAttribute("username","未登录");
		}
		return "index";
	}
	
	@RequestMapping(value = "/dashboard.do")  
	public String monitor(ModelMap model) {
		try{			
			BaseConfig baseConfig = platformDao.get(new BaseConfig());
			model.addAttribute("baseConfig", baseConfig);
			model.addAttribute("optrRole", "计量");
			Equipment equipment = new Equipment();
			equipment.setEqutype("C");
			List<Equipment> equipmentList = platformDao.queryList(equipment);
			model.addAttribute("equipmentList",equipmentList);
		}catch (Exception e){	
		}
		return "dashboard";
	}
	
	@RequestMapping(value = "/index")  
	public String index2(ModelMap model) {
		try{			
			BaseConfig baseConfig = platformDao.get(new BaseConfig());
			model.addAttribute("baseConfig", baseConfig);
		}catch (Exception e){	
		}
		return "index"; 
	}
	
	@RequestMapping(value = "/rule.do")  
	public String rold(ModelMap model) {
		return "rule"; 
	}
	
	@RequestMapping(value = "/flow.do")  
	public String flow(ModelMap model) {		
		return "flow"; 
	}
	
	@RequestMapping(value = "/display.do")  
	public String flags(ModelMap model) {
		List<Map<String,String>> displayList = cacheUtil.getCache("operatesCache").list(new HashMap<String,String>(),"");
		model.addAttribute("displayList", displayList);
		return "display"; 
	}
	
	@RequestMapping(value = "/privilege.do")  
	public String service(ModelMap model) {
		return "privilege"; 
	}
	
	@RequestMapping(value = "/logs.do")
	public String log(ModelMap model) {
		return "log"; 
	}
	
	@RequestMapping(value = "/baseinfo.do")  
	public String baseinfo(ModelMap model) {
		try{			
			BaseConfig baseConfig = platformDao.get(new BaseConfig());
			model.addAttribute("baseConfig", baseConfig);
		}catch (Exception e){	
		}
		return "baseinfo"; 
	}
	@RequestMapping(value = "/operaconfig.do")  
	public String operaconfig(ModelMap model) {
		List<Map<String,String>> displayList = cacheUtil.getCache("operatesCache").list(new HashMap<String,String>(),"");
		model.addAttribute("displayList", displayList);
		return "operaconfig"; 
	}
}
