package com.talent.measure.common;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.talent.base.annotation.Rule;
import com.talent.base.model.Message;
import com.talent.base.util.CacheUtil;
//import com.talent.measure.dao.BusinessConfigDao;
import com.talent.measure.model.FunctionLog;
import com.talent.measure.model.Measure;
import com.talent.measure.model.MeasureRule;
import com.talent.measure.model.MeasureRuleDetail;
import com.talent.base.model.ReturnMessage;

public class MeasureRuleCalc {
	
	@Autowired
	private CacheUtil cacheUtil;
	
	@Autowired
	private BusinessConfig businessConfig;
	//@Autowired
	//private BusinessConfigDao bcDao;

	public Message flagsCheck(Measure omeasure,String validplace){
		
		Message msg = new Message();
		String holeFlag = Message.MFUNCCONTINUE;		
		List<ReturnMessage> r = new ArrayList<ReturnMessage>();
		
		List<MeasureRule> mrs = cacheUtil.getCache("measureRuleCache").list(new MeasureRule(),new String[]{"opertype","measuretype","materialcode"},new String[]{cacheUtil.EQ,cacheUtil.EQ,cacheUtil.LIKE},new String[]{omeasure.getOperatype(),omeasure.getMeasurestate(),omeasure.getMaterialcode()});
		if(0 == mrs.size()){
			mrs = cacheUtil.getCache("measureRuleCache").list(new MeasureRule(),new String[]{"opertype","measuretype"},new String[]{cacheUtil.EQ,cacheUtil.EQ},new String[]{omeasure.getOperatype(),omeasure.getMeasurestate()});
		}
		if(mrs.size() > 1){
			for(MeasureRule mr : mrs){
				if(!"全部".equals(mr.getMaterialcode())){
					List<MeasureRuleDetail> measureRuleDetails = cacheUtil.getCache("measureRuleDetailCache").list(new MeasureRuleDetail(),new String[]{"pid"},new String[]{cacheUtil.EQ},new Object[]{mr.getId()});
					for(MeasureRuleDetail measureRuleDetail : measureRuleDetails){
						try {
							Method method = BeanUtils.findMethodWithMinimalParameters(BusinessConfig.class, measureRuleDetail.getFunctionname());
							Annotation p = method.getAnnotation(Rule.class);
							Method vp = p.getClass().getMethod("validplace");
							String vpv = (String)vp.invoke(p);
							if(validplace.equals(vpv) || Rule.ALL_VALID.equals(vpv)){
								ReturnMessage returnValue = (ReturnMessage)(method.invoke(businessConfig,omeasure));
								r.add(returnValue);
								if(3 == returnValue.getFlag()){
									holeFlag = returnValue.getFlag() + "";
									break;
								}else if(2 == returnValue.getFlag() || 1 == returnValue.getFlag()){
									holeFlag = returnValue.getFlag() + "";
								}
							}							
						}catch (Exception e){
							
						}
					}
				}				
			}
		}else if(1 == mrs.size()){
			List<MeasureRuleDetail> measureRuleDetails = cacheUtil.getCache("measureRuleDetailCache").list(new MeasureRuleDetail(),new String[]{"pid"},new String[]{cacheUtil.EQ},new Object[]{mrs.get(0).getId()});
			for(MeasureRuleDetail measureRuleDetail : measureRuleDetails){
				try {
					Method method = BeanUtils.findMethodWithMinimalParameters(BusinessConfig.class, measureRuleDetail.getFunctionname());
					Annotation p = method.getAnnotation(Rule.class);
					Method vp = p.getClass().getMethod("validplace");
					String vpv = (String)vp.invoke(p);
					if(validplace.equals(vpv) || Rule.ALL_VALID.equals(vpv)){
						ReturnMessage returnValue = (ReturnMessage)(method.invoke(businessConfig,omeasure));
						r.add(returnValue);
						if(3 == returnValue.getFlag()){
							holeFlag = returnValue.getFlag() + "";
							break;
						}else if(2 == returnValue.getFlag() || 1 == returnValue.getFlag()){
							holeFlag = returnValue.getFlag() + "";
						}
					}					
				}catch (Exception e){
					
				}
			}
		}
		
		msg.setMfunc(holeFlag);
		msg.setFlags(r);
		FunctionLog logs= new FunctionLog();
		String info="";
		String falseinfo="";
		for(int d=0;d<msg.getFlags().size();d++){
			ReturnMessage re= msg.getFlags().get(d);

			info = info+"20160630验证的方法数："+msg.getFlags().size()+" || "+"方法名称："+re.getFunctionname()+" 状态："+re.getSuccess()+" 信息： "+re.getMsg();
		
			if(("".equals(re.getMsg())) || re.getMsg()==null){
				falseinfo = falseinfo+" || "+"方法名称："+re.getFunctionname()+" 状态："+re.getSuccess()+" 信息：信息为空 ";
			}
		
		}
		logs.setParaminfo(info+"--||--"+falseinfo);
		logs.setFunctionname("functionname");
		//bcDao.insertfunctionlog(logs);
		
		return msg;
	}
	
}
