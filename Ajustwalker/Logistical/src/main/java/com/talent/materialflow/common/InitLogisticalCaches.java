package com.talent.materialflow.common;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.talent.core.cache.CacheService;
import com.talent.materialflow.model.LogisticalRule;
import com.talent.materialflow.model.LogisticalRuleDetail;
import com.talent.materialflow.service.mapper.BCommonMapper;

@Component
public class InitLogisticalCaches {
	
	@Autowired
	private CacheService cacheService;
	
	@Resource
	private BCommonMapper bcommonMapper;
	
	private static final Log logger = LogFactory.getLog(InitLogisticalCaches.class);
	
	@Scheduled(fixedDelay=1555200000000l,initialDelay=2000)//每10个月执行一次，相当于只执行一次，延迟5秒执行。
    public void initMaterialRuleCache() {
		try{
			logger.info("业务控制规则库读取开始.....................................");
			cacheService.getCache("logisticalRuleDetailCache").clear();
			cacheService.getCache("logisticalRuleCache").clear();
			
			List<LogisticalRule> mrs =bcommonMapper.queryFunction();//读取配置主表中的数据信息
			
			for(LogisticalRule mr : mrs){
				cacheService.getCache("logisticalRuleCache").put("logisticalRuleKey" + mr.getId(), mr);
			}

			List<LogisticalRuleDetail> mrds =bcommonMapper.queryFunctionDetail() ;//读取配置规则中子表的数据信息
			for(LogisticalRuleDetail mrd : mrds){
				cacheService.getCache("logisticalRuleDetailCache").put("logisticalRuleDetailKey" + mrd.getId(), mrd);
			}
			
			logger.info("业务控制规则库读取结束.....................................");
		}catch(Exception e){
			logger.info("业务控制规则库读取失败.....................................");
		}
	}
}
