package com.xgmes.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.talent.core.annotation.Rule;
import com.talent.core.cache.CacheService;
import com.talent.core.model.Message;
import com.xgmes.model.Currtemp;
import com.xgmes.model.LogisticalRule;
import com.xgmes.model.LogisticalRuleDetail;
import com.xgmes.model.ReturnMessage;

@Component
public class LogisticalRuleCalc {

	@Autowired
	private CacheService cacheService;

	@Autowired
	private BusinessConfig businessConfig;

	public Message flagsCheck(Currtemp curr, String validplace) {

		Message msg = new Message();
		String holeFlag = "0";
		List<ReturnMessage> r = new ArrayList<ReturnMessage>();
		// 根据条件查询缓存中的主表方法名称
		List<LogisticalRule> mrs = cacheService.getCache("logisticalRuleCache").list(new LogisticalRule(),
				new String[] { "opertype", "logisticaltype" }, new String[] { cacheService.EQ(), cacheService.EQ() },
				new String[] { curr.getOperatype(), curr.getProcesslink() });
		if (mrs != null) {
			if (mrs.size() > 1) {
				for (LogisticalRule mr : mrs) {
					if (!"全部".equals(mr.getMaterialcode())) {
						List<LogisticalRuleDetail> logisticalRuleDetails = cacheService
								.getCache("logisticalRuleDetailCache").list(new LogisticalRuleDetail(),
										new String[] { "pid" }, new String[] { cacheService.EQ() },
										new Object[] { mr.getId() });
						for (LogisticalRuleDetail logisticalRuleDetail : logisticalRuleDetails) {
							try {
								Method method = BeanUtils.findMethodWithMinimalParameters(BusinessConfig.class,
										logisticalRuleDetail.getFunctionname());
								Annotation p = method.getAnnotation(Rule.class);
								Method vp = p.getClass().getMethod("validplace");
								String vpv = (String) vp.invoke(p);
								if (validplace.equals(vpv) || Rule.ALL_VALID.equals(vpv)) {
									ReturnMessage returnValue = (ReturnMessage) (method.invoke(businessConfig,
											cacheService));
									r.add(returnValue);
									if (3 == returnValue.getFlag()) {
										holeFlag = returnValue.getFlag() + "";
										break;
									} else if (2 == returnValue.getFlag() || 1 == returnValue.getFlag()) {
										holeFlag = returnValue.getFlag() + "";
									}
								}
							} catch (Exception e) {

							}
						}
					}
				}
			} else if (1 == mrs.size()) {
				// 根据条件查询子表中的信息字段
				List<LogisticalRuleDetail> logisticalRuleDetails = cacheService.getCache("logisticalRuleDetailCache")
						.list(new LogisticalRuleDetail(), new String[] { "pid" }, new String[] { cacheService.EQ() },
								new Object[] { mrs.get(0).getId() });
				for (LogisticalRuleDetail logisticalRuleDetail : logisticalRuleDetails) {
					try {
						Method method = BeanUtils.findMethodWithMinimalParameters(BusinessConfig.class,
								logisticalRuleDetail.getFunctionname());
						Annotation p = method.getAnnotation(Rule.class);
						Method vp = p.getClass().getMethod("validplace");
						String vpv = (String) vp.invoke(p);
						if (validplace.equals(vpv) || Rule.ALL_VALID.equals(vpv)) {
							ReturnMessage returnValue = (ReturnMessage) (method.invoke(businessConfig, curr));
							r.add(returnValue);
							if (3 == returnValue.getFlag()) {
								holeFlag = returnValue.getFlag() + "";
								break;
							} else if (2 == returnValue.getFlag() || 1 == returnValue.getFlag()) {
								holeFlag = returnValue.getFlag() + "";
							}
						}
					} catch (Exception e) {

					}
				}
			}
		}

		msg.setMfunc(holeFlag);
		msg.setFlags(r);
		return msg;
	}

}
