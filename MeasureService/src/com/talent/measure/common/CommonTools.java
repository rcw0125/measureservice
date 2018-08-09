package com.talent.measure.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.talent.base.dao.PlatformDao;
import com.talent.base.util.CacheUtil;
import com.talent.measure.dao.MeasureDao;
import com.talent.measure.model.DeviceConfig;
import com.talent.measure.model.MeasureRule;
import com.talent.measure.model.MeasureRuleDetail;
import com.talent.measure.model.TaskCode;
import com.talent.privilege.model.Resource;

public class CommonTools {
	
	@Autowired
	private PlatformDao platformDao;
			
	@Autowired
	private CacheUtil cacheUtil;
	
	@Autowired
	private MeasureDao measureDao;
	
	private static final Log logger = LogFactory.getLog(CommonTools.class);

	public void syncCacheData(){    	
    	try{
    		logger.info("计量业务数据缓存开始.....................................");
    		
    		syncMeasureBaseData();
    		
    		syncResourceData();
    		
    		syncMeasureRuleData();
    		
    		syncDeviceConfigData();
    		
    		logger.info("计量业务数据缓存结束.....................................");
    	}catch(Exception e){
    		logger.info("计量业务数据缓存失败....................................");
    	}
	}
	
	public void syncMeasureBaseData(){    	
		try{
			logger.info("基础数据读取开始.....................................");
			cacheUtil.getCache("operatesCache").clear();
			cacheUtil.getCache("materialsCache").clear();
			cacheUtil.getCache("taskcodesCache").clear();
			
			List<Map<String,String>> operates = measureDao.getOperateType();
			for(Map<String,String> t : operates){
				cacheUtil.getCache("operatesCache").put("operateskey" + t.get("OPERATYPE"),t);
			}
			
			List<Map<String,String>> materials = measureDao.getMaterialClassesByName();
			for(Map<String,String> t : materials){
				cacheUtil.getCache("materialsCache").put("materialskey" + t.get("MATERIALNAME"),t);
			}
			
			List<TaskCode> taskcodes = measureDao.getAllTaskcodes();
			for(TaskCode t : taskcodes){
				cacheUtil.getCache("taskcodesCache").put("taskcodekey" + t.getTaskcode(),t);
			}
			
			logger.info("基础数据读取结束.....................................");
		}catch(Exception e){
			logger.info("基础数据读取失败....................................");
		}
	}
	
	public void syncDeviceConfigData(){    	
		try{
			logger.info("硬件控制规则读取开始.....................................");
			cacheUtil.getCache("deviceconfigCache").clear();
			
			List<DeviceConfig> deviceConfigList = platformDao.queryAllList(new DeviceConfig());
			cacheUtil.getCache("deviceconfigCache").clear();
			for(DeviceConfig t : deviceConfigList){
				cacheUtil.getCache("deviceconfigCache").put("deviceconfig:" + t.getDeviceName() + t.getCtrlType() + t.getCtrlGoal() + t.getConfigDetail(),t);
			}
			logger.info("硬件控制规则读取结束.....................................");
		}catch(Exception e){
			logger.info("硬件控制规则读取失败....................................");
		}
	}
	
	public void syncResourceData(){    	
		try{
			logger.info("资源基础数据读取开始.....................................");
			
			cacheUtil.getCache("resourceCache").clear();
			Resource r = new Resource();
			List<Resource> allResource = platformDao.queryList(r);
			for(Resource resource : allResource){
				cacheUtil.getCache("resourceCache").put("resourcekey" + resource.getId(),resource);
			}
			
			logger.info("资源基础数据读取结束.....................................");
		}catch(Exception e){
			logger.info("资源基础数据读取失败....................................");
		}
	}
	
	public void syncMeasureRuleData(){    	
		try{
			logger.info("业务控制规则库读取开始.....................................");
			cacheUtil.getCache("measureRuleDetailCache").clear();
			cacheUtil.getCache("measureRuleCache").clear();
			MeasureRule mr_t = new MeasureRule();
			mr_t.setMaterialcode("");
			List<MeasureRule> mrs = platformDao.queryList(mr_t);
			for(MeasureRule mr : mrs){
				cacheUtil.getCache("measureRuleCache").put("measureRuleKey" + mr.getId(), mr);
			}
			
			MeasureRuleDetail mrd_t = new MeasureRuleDetail();
			mrd_t.setPid(-1);
			List<MeasureRuleDetail> mrds = platformDao.queryList(mrd_t);
			for(MeasureRuleDetail mrd : mrds){
				cacheUtil.getCache("measureRuleDetailCache").put("measureRuleDetailKey" + mrd.getId(), mrd);
			}
			logger.info("业务控制规则库读取结束.....................................");
		}catch(Exception e){
			logger.info("业务控制规则库读取失败....................................");
		}
	}
}
