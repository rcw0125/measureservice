package com.talent.privilege.web;

import com.talent.base.dao.PlatformDao;
import com.talent.base.model.BaseEntity;
import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.base.util.BaseUtil;
import com.talent.privilege.dao.PrivilegeDao;
import com.talent.privilege.model.Resource;
import com.talent.privilege.model.Role;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResourceController extends BaseController {

	@Autowired
	private PlatformDao platformDao;
	
	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Autowired
	private JobLauncher jobLauncher;

	@ResponseBody
	@RequestMapping(value = "/resource/tree.do")  
	public List<BaseEntity> tree(Resource resource,ModelMap model) {
		
		List<BaseEntity> treeList = new ArrayList<BaseEntity>();
		try{
			platformDao.queryTree(resource,0, null, treeList);
		}catch (Exception e) {	
			treeList = new ArrayList<BaseEntity>();
		}
		return treeList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/addoredit.do")  
	public Message addorEdit(Resource resource,ModelMap model) {
		
		Message msg = new Message();
		try{
			if(-1 == resource.getId()){
				platformDao.getNewID(resource);
				platformDao.insert(resource);
			}else{
				platformDao.update(resource);
			}
			try{
				Job job = (Job)BaseUtil.getApplicationContext().getBean("resourceSyncJob");
				jobLauncher.run(job, new JobParametersBuilder()
						  .addString("createTime",Calendar.getInstance().getTimeInMillis()+"")
						  .toJobParameters());
			}catch(Exception e){
				
			}			
			msg.setTotal(resource.getId());
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/del.do")  
	public Message del(Resource resource,String ids,ModelMap model) {
		
		Message msg = new Message();
		try{
			platformDao.remove(resource, ids);
			try{
				Job job = (Job)BaseUtil.getApplicationContext().getBean("resourceSyncJob");
				jobLauncher.run(job, new JobParametersBuilder()
						  .addString("createTime",Calendar.getInstance().getTimeInMillis()+"")
						  .toJobParameters());
			}catch(Exception e){
				
			}
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/loadform.do")  
	public Resource loadForm(Resource resource,ModelMap model) {
		
		try{
			if(-1 != resource.getId()){
				resource = platformDao.get(resource);
			}else{
				resource.setIsdisplay(1);
			}
			if(null == resource){
				resource = new Resource();
				resource.setIsdisplay(1);
				resource.setResourcetype("系统模块");
			}
		}catch (Exception e) {	
			resource = new Resource();
			resource.setIsdisplay(1);
		}
		return resource; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/queryList.do")  
	public List<Role> queryList(Resource resource,ModelMap model) {
		
		List<Role> list = null;
		try{
			list = privilegeDao.selectResourceRole(resource);
		}catch (Exception e){	
			list = new ArrayList<Role>();
		}
		return list; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/resource/setrole.do")
	public Message setRole(Resource resource,String optr,ModelMap model) {
		
		Message msg = new Message();
		try{
			if("添加".equals(optr)){
				privilegeDao.insertResourceRole(resource);
			}else{
				privilegeDao.deleteResourceRole(resource);
			}
		}catch (Exception e){	
			
		}
		return msg; 
	}
}
