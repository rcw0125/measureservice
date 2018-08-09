package com.talent.measure.web;

import com.talent.base.annotation.Rule;
import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.base.util.BaseUtil;
import com.talent.base.util.CacheUtil;
import com.talent.base.util.WebUtils;
import com.talent.measure.common.BusinessConfig;
import com.talent.measure.model.MeasureRule;
import com.talent.measure.model.MeasureRuleDetail;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("unchecked")
@Controller
public class MeasureRuleController extends BaseController{
	
	@Autowired
	private PlatformDao platformDao;
	
	@Autowired
	private BusinessConfig businessConfig;
	
	@Autowired
	private CacheUtil cacheUtil;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/queryPage.do")  
	public List<MeasureRule> queryPage(MeasureRule measureRule,ModelMap model) {
		
		List<MeasureRule> data = null;
		try{
			data = platformDao.queryList(measureRule);
		}catch (Exception e){	
			data = new ArrayList<MeasureRule>();
		}
		return data; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/queryDetail.do")  
	public List<Map<String, Object>> queryDetail(MeasureRuleDetail measureRuleDetail,String operateType,ModelMap model) {
		
		List<Map<String, Object>> selectedMethods = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> unSelectedMethods = new ArrayList<Map<String, Object>>();
		
		List<MeasureRuleDetail> list = platformDao.queryList(measureRuleDetail);
		Map<String,Object> oc = (Map<String,Object>)cacheUtil.getCache("operatesCache").get("operateskey" + operateType);
		try{
			Method[] methods = businessConfig.getClass().getDeclaredMethods();
			for(Method method : methods){						
				if (method.isAnnotationPresent(Rule.class)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("selected",0);
					map.put("id",-1);
					map.put("pid",-1);
					String mn = method.getName();
					for(MeasureRuleDetail mrd : list){
						if(mn.equals(mrd.getFunctionname())){
							list.remove(mrd);
							map.put("selected",1);
							map.put("id", mrd.getId());
							map.put("pid", mrd.getPid());
							break;
						}
					}
					map.put("functionname",mn);
					Annotation p = method.getAnnotation(Rule.class);
					Method name = p.getClass().getMethod("name");
					map.put("functiondesc", name.invoke(p));
					Method ctrlflag = p.getClass().getMethod("ctrlflag");
					
					String cf = ctrlflag.invoke(p).toString();
					String[] cfs = cf.split(",");
					String ctrldesc = "不检查";
					for(String s : cfs){
						Object o = null !=oc ? oc.get(s.toUpperCase()) : null;
						if(null != o){
							s = o.toString();
							if("1".equals(s)){
								if("不检查".equals(ctrldesc)){
									ctrldesc = "进行提示";
								}
							}else if("2".equals(s)){
								if("进行提示".equals(ctrldesc) || "不检查".equals(ctrldesc)){
									ctrldesc = "进行选择";
								}
							}else if("3".equals(s)){
								if("进行选择".equals(ctrldesc) || "进行提示".equals(ctrldesc) || "不检查".equals(ctrldesc)){
									ctrldesc = "禁止计量";
								}							
							}
						}
					}
					map.put("ctrldesc",ctrldesc);
					Method memo = p.getClass().getMethod("memo");
					map.put("functionmemo", memo.invoke(p));
					if(Integer.parseInt(map.get("selected").toString()) == 1){
						selectedMethods.add(map);
					}else{
						unSelectedMethods.add(map);
					}
				}
			}
			selectedMethods.addAll(unSelectedMethods);
			if(null != list && list.size() > 0){
				try{
					for(MeasureRuleDetail mrd : list){
						platformDao.remove(mrd, mrd.getId()+"");
					}
					refreshServerCache();
				}catch (Exception e){	
					
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return selectedMethods;
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/addoredit.do")  
	public Message addorEdit(MeasureRule measureRule,ModelMap model) {
		
		Message msg = new Message();
		int id = -1;
		try{
			if(-1 == measureRule.getId()){
				id = platformDao.getNewID(measureRule);
				platformDao.insert(measureRule);
			}else{
				id = measureRule.getId();
				platformDao.update(measureRule);
			}
			msg.setTotal(id);
			msg.setMore(measureRule.getOpertype());
			
			refreshServerCache();
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/addorDeleteDetail.do")  
	public Message addDetail(MeasureRuleDetail measureRuleDetail,String optr,ModelMap model) {
		
		Message msg = new Message();
		try{
			if("添加".equals(optr)){
				try{
					platformDao.getNewID(measureRuleDetail);
					platformDao.insert(measureRuleDetail);
				}catch (Exception e) {	
					msg.setSuccess(false);
					msg.setMsg("操作失败！");
				}
			}else{
				try{
					platformDao.remove(measureRuleDetail, measureRuleDetail.getId()+"");
				}catch (Exception e) {	
					msg.setSuccess(false);
					msg.setMsg("操作失败！");
				}
			}
			refreshServerCache();
		}catch(Exception e){
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/del.do")  
	public Message del(MeasureRule measureRule,String ids,ModelMap model) {
		
		Message msg = new Message();
		try{
			platformDao.remove(measureRule, ids);
			refreshServerCache();
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	private void refreshServerCache(){
		try{
			String[] ips = new String[]{"10.1.196.87","10.1.196.88"};
			for(String ip : ips){
				String url = "http://" + ip + ":8080/MeasureService/measurerule/refresh.do";
				WebUtils.get(url,"r=1");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/refresh.do")
	public Message refresh(ModelMap model) {
		Message msg = new Message();
		try{
			Job job = (Job)BaseUtil.getApplicationContext().getBean("ruleSyncJob");
			jobLauncher.run(job, new JobParametersBuilder()
					  .addString("createTime",Calendar.getInstance().getTimeInMillis()+"")
					  .toJobParameters());
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/loadform.do")  
	public MeasureRule loadForm(MeasureRule measureRule,ModelMap model) {
		
		try{
			if(-1 == measureRule.getId()){
				measureRule = new MeasureRule();
			}else{
				measureRule = platformDao.get(measureRule);
			}
		}catch (Exception e) {	
			measureRule = new MeasureRule();
		}
		return measureRule == null ? new MeasureRule() : measureRule; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/loadformdetail.do")  
	public MeasureRuleDetail loadFormDetail(MeasureRuleDetail measureRuleDetail,ModelMap model) {
		
		MeasureRuleDetail measureRuleDetail2 = null;
		try{
			measureRuleDetail2 = platformDao.get(measureRuleDetail);
		}catch (Exception e) {	
			measureRuleDetail2 = measureRuleDetail;
		}
		return measureRuleDetail2 == null ? measureRuleDetail : measureRuleDetail2; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/loadoperatetype.do")
	public Message loadOperate(String q,ModelMap model) {
		Message msg = new Message();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try{
			HashMap<String,String> hm = new HashMap<String,String>();
			List<Map<String,String>> mList = cacheUtil.getCache("operatesCache").list(hm, "operateskey");
			for(Map<String,String> item : mList){
				hm = new HashMap<String,String>();
				hm.put("id",item.get("OPERATYPE"));
				hm.put("text",item.get("OPERANAME"));
				list.add(hm);
			}
		}catch (Exception e){
			
		}
		msg.setRows(list);
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/loadmaterialcode.do")
	public Message loadMaterialcode(String q,ModelMap model) {
		Message msg = new Message();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try{
			HashMap<String,String> hm = new HashMap<String,String>();
			hm.put("id", "-1");
			hm.put("text", "全部");
			list.add(hm);
			/*List<Map<String,String>> mList = cacheUtil.getCache("materialsCache").list(hm, "materialskey");
			for(Map<String,String> item : mList){
				hm = new HashMap<String,String>();
				hm.put("id",item.get("MATERIALCODE"));
				hm.put("text",item.get("MATERIALNAME"));
				list.add(hm);
			}*/
		}catch (Exception e){
			
		}
		msg.setRows(list);
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/measurerule/loadmeasuretype.do")  
	public Message loadMeasureType(String q,ModelMap model) {
		Message msg = new Message();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try{
			HashMap<String,String> hm = new HashMap<String,String>();
			hm.put("id", "G");
			hm.put("text", "计毛");
			list.add(hm);
			hm = new HashMap<String,String>();
			hm.put("id", "T");
			hm.put("text", "计皮");
			list.add(hm);
		}catch (Exception e) {	
			
		}
		msg.setRows(list);
		return msg;
	}
}
