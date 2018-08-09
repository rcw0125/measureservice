package com.talent.measure.common;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.talent.base.util.CacheUtil;
import com.talent.measure.dao.MeasureDao;
import com.talent.measure.model.FlowInParams;
import com.talent.measure.model.Measure;

public class HardwareCtrl {

	@Autowired
	private MeasureDao measureDao;
	
	@Autowired
	private CacheUtil cacheUtil;

	@SuppressWarnings("unchecked")
	public String hardwareCtrl(Measure measure,String deviceName,String[] ctrlTypes,String[] ctrlGoals){
		String result = ctrlGoals[1];
		String configDetail = "";
		try{
			for(String ctrlType : ctrlTypes){
				for(String ctrlGoal : ctrlGoals){				
					if("业务类型".equals(ctrlType)){
						configDetail = ((Map<String,String>)(cacheUtil.getCache("operatesCache").get("operateskey" + measure.getOperatype()))).get("OPERANAME");
					}else if("物料名称".equals(ctrlType)){
						configDetail = measure.getMaterialname();
					}else if("物料大类".equals(ctrlType)){
						configDetail = ((Map<String,String>)(cacheUtil.getCache("materialsCache").get("materialskey" + measure.getMaterialname()))).get("FOLDERNAME");
					}
					if(null != configDetail){
						Object temp = cacheUtil.getCache("deviceconfigCache").get("deviceconfig:" + deviceName + ctrlType + ctrlGoal + configDetail);
						if(null != temp){
							result = ctrlGoal;
						}
					}
				}
			}
		}catch(Exception e){
			
		}
		return result;
	}
	
	public String getMeasureStyle(Measure measure){
		String[] ctrlTypes = new String[]{"业务类型","物料名称","物料大类"};
		String[] ctrlGoals = new String[]{"强制自助","远程计量","自助计量","强制远程"};
		return hardwareCtrl(measure,"终端机",ctrlTypes,ctrlGoals);
	}
	
	public Map<String,Object> getPrinterStyle(FlowInParams params){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("name", "打印磅单");
		//result.put("check","强制禁止");
		result.put("check","强制启用");
		
	/*	HashMap<String,String> printerParams = measureDao.getPrintBillStyle(params.getMatchid());
		if(null != printerParams){
			if("只计毛".equals(params.getOptr())){
				if(null != printerParams.get("PRINTSETGROSS")){
					result.put("check","强制启用");
				}
			}else if("只计皮".equals(params.getOptr())){
				if(null != printerParams.get("PRINTSETTARE")){
					result.put("check","强制启用");
				}
			}else if("出净重".equals(params.getOptr())){
				if(null != printerParams.get("PRINTSETSUTTLE")){
					result.put("check","强制启用");
				}
			}
		}else{
			System.out.println("#############获取打印控制参数失败#############");
		}*/
		
		HashMap<String,String> roles = new HashMap<String,String>();
		roles.put("mfunc", "0");
		roles.put("otherParams",params.getOptr());
		result.put("roles", roles);
		return result;
	}
	
	public Map<String,Object> getConfigPrintStyle(FlowInParams params){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("name", "打印磅单");
		result.put("check","强制禁止");
		
		HashMap<String,String> printerParams = measureDao.getConfigPrintStyle(params.getMatchid());
		if(null != printerParams){
			if("只计毛".equals(params.getOptr())){
				if(null != printerParams.get("PRINTSETGROSS")){
					result.put("check","强制启用");
				}
			}else if("只计皮".equals(params.getOptr())){
				if(null != printerParams.get("PRINTSETTARE")){
					result.put("check","强制启用");
				}
			}else if("出净重".equals(params.getOptr())){
				if(null != printerParams.get("PRINTSETSUTTLE")){
					result.put("check","强制启用");
				}
			}
		}else{
			System.out.println("#############获取打印控制参数失败#############");
		}
		
		HashMap<String,String> roles = new HashMap<String,String>();
		roles.put("mfunc", "0");
		roles.put("otherParams",params.getOptr());
		result.put("roles", roles);
		return result;
	}
	
	public Map<String,Object> getCarLoactionCtrlStyle(Measure measure){
		Map<String,Object> result = new HashMap<String,Object>();
		String[] ctrlTypes = new String[]{"业务类型","物料名称","物料大类"};
		String[] ctrlGoals = new String[]{"强制启用","强制禁用"};
		result.put("name", "红外对射");
		result.put("check", hardwareCtrl(measure,"红外对射",ctrlTypes,ctrlGoals));
		HashMap<String,String> roles = new HashMap<String,String>();
		roles.put("mfunc", "2");
		roles.put("otherParams","");
		result.put("roles", roles);
		return result;
	}
}
