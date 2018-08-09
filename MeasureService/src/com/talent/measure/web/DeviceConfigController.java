package com.talent.measure.web;

import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.base.util.BaseUtil;
import com.talent.measure.model.BaseConfig;
import com.talent.measure.model.DeviceConfig;

import java.util.Calendar;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceConfigController extends BaseController{
	
	@Autowired
	private PlatformDao platformDao;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@ResponseBody
	@RequestMapping(value = "/deviceconfig/query.do")
	public List<DeviceConfig> query(DeviceConfig deviceConfig,ModelMap model) {
		List<DeviceConfig> detailList = null;
		try{
			detailList = platformDao.queryList(deviceConfig);
		}catch(Exception e){
			
		}
		return detailList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/baseconfig/update.do")
	public Message updateBaseConfig(BaseConfig baseConfig,ModelMap model) {
		Message msg = new Message();
		try{
			platformDao.update(baseConfig);
			try{
				Job job = (Job)BaseUtil.getApplicationContext().getBean("hardwareSyncJob");
				jobLauncher.run(job, new JobParametersBuilder()
						  .addString("createTime",Calendar.getInstance().getTimeInMillis()+"")
						  .toJobParameters());
			}catch(Exception e){
				
			}
		}catch(Exception e){
			msg.setSuccess(false);
			msg.setMsg("更新基础配置失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deviceconfig/save.do")
	public Message save(DeviceConfig deviceConfig,String details,ModelMap model) {
		Message msg = new Message();
		try{
			for(String materialname : details.split(",")){
				platformDao.getNewID(deviceConfig);
				deviceConfig.setConfigDetail(materialname);
				platformDao.insert(deviceConfig);
			}
			try{
				Job job = (Job)BaseUtil.getApplicationContext().getBean("hardwareSyncJob");
				jobLauncher.run(job, new JobParametersBuilder()
						  .addString("createTime",Calendar.getInstance().getTimeInMillis()+"")
						  .toJobParameters());
			}catch(Exception e){
				
			}
		}catch(DuplicateKeyException ee){
			msg.setSuccess(false);
			msg.setMsg("不能添加重复的项目！");
		}catch(Exception e){
			msg.setSuccess(false);
			msg.setMsg("更新硬件配置失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deviceconfig/remove.do")
	public Message remove(DeviceConfig deviceConfig,String id,ModelMap model) {
		Message msg = new Message();
		try{
			platformDao.remove(deviceConfig, id);
			try{
				Job job = (Job)BaseUtil.getApplicationContext().getBean("hardwareSyncJob");
				jobLauncher.run(job, new JobParametersBuilder()
						  .addString("createTime",Calendar.getInstance().getTimeInMillis()+"")
						  .toJobParameters());
			}catch(Exception e){
				
			}
		}catch(Exception e){
			msg.setSuccess(false);
			msg.setMsg("更新硬件配置失败！");
		}
		return msg;
	}
}
