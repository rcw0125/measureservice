package com.talent.privilege.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.core.model.Message;
import com.talent.core.privilege.model.Resource;
import com.talent.core.privilege.model.User;
import com.talent.core.realtime.service.PushMessageUtil;
import com.talent.core.util.HttpUtils;
import com.talent.core.util.ResourceUtils;
import com.talent.privilege.model.UserMS;
import com.talent.privilege.service.ResourceService2;
import com.talent.privilege.service.mapper.DashboardMapper;


@Controller
@RequestMapping
public class SystemController {
	
	@Autowired
	private ResourceService2 resourceService;
	
	@Autowired
	private DashboardMapper dashboardMapper;
	
	@Autowired
	private HttpUtils httpUtils;
	
	@Autowired
	private ResourceUtils resourceUtils;
	
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/")
	public String index(ModelMap model,HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping(value = "/dashboard")
	public String dashboard(ModelMap model) {
		return "dashboard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/unauth/measure/login")
	public Message measureUserLogin(User user,HttpServletRequest request) {
		
		Message msg = new Message();		
		try {
			UserMS u = dashboardMapper.getMeasureUserByName(user.getUsername());
			if(null == u){
				msg.setSuccess(false);
				msg.setMsg("登录失败，用户名错误！");
			}else if(!u.getServicing().equals("0")){
				msg.setSuccess(false);
				msg.setMsg("用户已禁用，不允许登录！");
			}else{
				boolean match = passwordEncoder.matches(user.getPassword(), u.getPassword());
				if(match){
					msg.setData(dashboardMapper.getMeasureUserPrivileges(user.getUsername()));
					msg.setMore(u);
				}else{
					msg.setSuccess(false);
					msg.setMsg("登录失败，密码错误！");
				}
			}
		} catch (Exception e){
			msg.setSuccess(false);
			e.printStackTrace();
			msg.setMsg("登录失败，用户名和密码错误！");
		}
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/main")
	public String main(ModelMap model,HttpServletRequest request,String access_token,String userId) {
		String style = "LANDSCAPE";
		try{
			List<Map<String,String>> menuList = new ArrayList<Map<String,String>>();
			List<Resource> allResource = resourceService.findResourceByDisplay(1);
			List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			User ud = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			style = ud.getReserve1();
			//查找所有资源
			List<Resource> defineUserResources = new ArrayList<Resource>();
			for(Resource item : allResource){
				for(GrantedAuthority ga : grantedAuthorities){
					if(item.getResourcecode().equals(ga.getAuthority()) && !defineUserResources.contains(item)){
						defineUserResources.add(item);
						break;
					}
				}
			}
			
			for(Resource item : defineUserResources){
				if(0 == item.getFid()){
					StringBuffer submenu = new StringBuffer();
					String storeTokenScript = "";
					String subSystemLink = "";
					boolean isCurrentSystem = true;
					if(!"QuanXian".equals(item.getResourcecode())){
						isCurrentSystem = false;
						subSystemLink = item.getResourcelink();
						storeTokenScript = "<script type=\"text/javascript\">token('" + item.getResourcelink() + "','" + access_token + "');</script>";
					}else{
						subSystemLink = request.getContextPath();
					}
					
					Map<String,String> menusMap = new HashMap<String,String>();
					if("LANDSCAPE".equals(style)){
						landscapeStyle(defineUserResources,item.getId(),submenu,subSystemLink,isCurrentSystem);
						menusMap.put("ROOT","<a href=\"javascript:void(0)\"><i class=\"" + item.getResourceicon() + "\"></i><span>" + item.getResourcename() + "系统</span><span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i>"+storeTokenScript+"</span></a>");
						menusMap.put("SUBS",submenu.toString());
					}else{
						portraitStyle(defineUserResources,item.getId(),submenu,subSystemLink,isCurrentSystem);
						menusMap.put("ROOT","<span class=\"" + item.getResourceicon() + "\"></span>" + storeTokenScript + "&nbsp;" + item.getResourcename());
						menusMap.put("SUBS",submenu.toString());
					}
					menuList.add(menusMap);
				}
			}
			model.addAttribute("menus",menuList);
		}catch(Exception e){
			model.addAttribute("menus",null);
		}
		return style.toLowerCase();
	}
	
	private void portraitStyle(List<Resource> resourceArray,long fid,StringBuffer result,String contextName,boolean currentSystem){
    	StringBuffer sb = new StringBuffer();
    	for(Resource r_t : resourceArray){
    		if(fid == r_t.getFid()){
    			boolean hasChildren = false;
    			for(Resource sr_t : resourceArray){
    				if(r_t.getId() == sr_t.getFid()){
    					hasChildren = true;
    					break;
    				}
    			}
    			if(hasChildren){
    				StringBuffer ssb = new StringBuffer();
    				sb.append("<li><a href=\"javascript:void(0)\"><span class=\"" + r_t.getResourceicon() + "\"></span>&nbsp;" + r_t.getResourcename() + "<span class=\"caret\"></span></a>");
    				sb.append("<ul class=\"dropdown-menu\">");
    				portraitStyle(resourceArray,r_t.getId(),ssb,contextName,currentSystem);
    				sb.append(ssb);
    				sb.append("</ul>");
    				sb.append("</li>");
    			}else{
    				String href = "javascript:void(0)";
    				if(null != r_t.getResourcelink()){
    					href = contextName + r_t.getResourcelink();
    				}
    				sb.append("<li><a class=\"J_menuItem\" href=\"" + href + "\"><span class=\"" + r_t.getResourceicon() + "\"></span>&nbsp;"+r_t.getResourcename()+"</a></li>");
    			}
			}
    	}
    	result.append(sb);
    }
    
    private void landscapeStyle(List<Resource> resourceArray,long fid,StringBuffer result,String contextName,boolean currentSystem){
    	StringBuffer sb = new StringBuffer();
    	for(Resource r_t : resourceArray){
    		if(fid == r_t.getFid()){
    			boolean hasChildren = false;
    			for(Resource sr_t : resourceArray){
    				if(r_t.getId() == sr_t.getFid()){
    					hasChildren = true;
    					break;
    				}
    			}
    			if(hasChildren){
    				StringBuffer ssb = new StringBuffer();
    				sb.append("<li>");
    				sb.append("<a href=\"javascript:void(0)\"><i class=\"" + r_t.getResourceicon() + "\"></i> " + r_t.getResourcename() + "<span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span></a>");
    				sb.append("<ul class=\"treeview-menu\">");
    				landscapeStyle(resourceArray,r_t.getId(),ssb,contextName,currentSystem);
    				sb.append(ssb);
    				sb.append("</ul>");
    				sb.append("</li>");
    			}else{
    				String href = "javascript:void(0)";
    				if(null != r_t.getResourcelink()){
    					href = contextName + r_t.getResourcelink();
    				}
    				sb.append("<li><a class=\"J_menuItem\" href=\"" + href + "\"><i class=\"" + r_t.getResourceicon() + "\"></i> " + r_t.getResourcename() + "</a></li>");
    			}
    		}
    	}
    	result.append(sb);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/login")
	public String login(ModelMap model,User user,String style,HttpServletRequest request) {
		String token = httpUtils.formPost("http://localhost:"+request.getLocalPort() + request.getContextPath() + "/oauth/token","grant_type=password&username=" + user.getUsername() + "&password=" + user.getPassword() + "&client_id=" + resourceUtils.getResource("app.client.id") + "&client_secret=" + resourceUtils.getResource("app.client.secret"));
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map<String,Object> responseBody = objectMapper.readValue(token,HashMap.class);
			if(null != responseBody && null != responseBody.get("access_token")){
				return "redirect:/main?access_token=" + responseBody.get("access_token");
			}else{
				model.addAttribute("authentication_error",1);
				return "redirect:/";
			}
		} catch (Exception e){
			model.addAttribute("authentication_error",2);
			return "redirect:/";
		}
	}
    
    @RequestMapping(value = "/unauth/logout.do")
	public String logout(ModelMap model,HttpServletRequest request) {
		/*cacheService.getCache("TokenCache").remove("AccessToken" + access_token);
		Cookie cs[] = request.getCookies();
		String css = "";
		for(Cookie c : cs){
			css = css + "," + c.getName();
		}
		css = css.substring(1);
		model.addAttribute("clear",css);*/
		return "index";
	}
	
	@RequestMapping(value="/upgradenotice")
	public String enterSystemConfig(){
		return "upgradenotice";
	}
	
	@ResponseBody
	@RequestMapping(value="/sendmsgtoall")
	public Message sendMsgToPerson(HttpServletRequest request,String content) {
		try{
			PushMessageUtil.sendMessageToAll(content);
			return new Message(true,"发送消息成功！");
		}catch(Exception e){
			return new Message(false,"发送消息失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getreminders")
	public Message getReminders() {
		try{
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//user.setDisplayname("李均会");
			
			List<Map<String,Object>> reminders = dashboardMapper.getReminders(user.getDisplayname());
			if(reminders.size() != 0){
				Message msg = new Message();
				msg.setData(reminders);
				msg.setMsg("您有待办事项，请前往查看");
				return msg;
			}else{
				return new Message(false,"没有待办事项！");
			}
		}catch(Exception e){
			return new Message(false,"获取待办事项失败！");
		}
	}
	
	@RequestMapping(value="/unauth/clientdownload")
	public String downloadClient() {
		return "clientdownload";
	}
	
	@ResponseBody
	@RequestMapping(value="/querycount")
	public Message queryCount() {
		try{
			Message msg = new Message();
			String isparameter =  dashboardMapper.isparameter(23);
			if(isparameter.equals("1")){
				int count= dashboardMapper.queryCount();
				msg.setData(count);
				msg.setSuccess(true);
			}else{
				msg.setSuccess(false);
			}
			return msg;
		}catch(Exception e){
			return new Message(false,"获取参数失败");
		}
	}
	@ResponseBody
	@RequestMapping("/unauth/measure/getAllMeasureUser.do")
	public List<UserMS> getAllMeasureUser() throws Exception {		
		return dashboardMapper.getAllMeasureUser();
	}
	/**
	 * 坐席密码修改服务
	 */
	@ResponseBody
	@RequestMapping("/unauth/measure/updatePassword.do")
	public Message updatePassword(UserMS user) throws Exception {
		Message msg = new Message();
		user.setUsercode(user.getUsercode());
		UserMS u = dashboardMapper.queryPassword(user);
		if(null == u){
			msg.setSuccess(false);
			msg.setMsg("用户名输入错误！");
		}else{
			try{
				if (passwordEncoder.matches(user.getRepassword(), u.getPassword())) {
					user.setPassword(passwordEncoder.encode(user.getPassword()));
					if(dashboardMapper.updatePassword(user)>0){
						msg.setSuccess(true);
						msg.setMsg("密码修改成功！");
					}
				}else{
					msg.setSuccess(false);
					msg.setMsg("原密码填写不正确！");
				}
			}catch(Exception e){
				msg.setSuccess(false);
				msg.setMsg("修改密码失败！");
			}
		}
		return msg;
	}
}