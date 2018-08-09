package com.talent.measure.web;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.measure.dao.BusinessConfigDao;
import com.talent.measure.dao.CommonDao;
import com.talent.measure.dao.MeasureDao;
import com.talent.measure.dao.MeasureTaskReportDao;
import com.talent.measure.model.FunctionLog;
import com.talent.measure.model.Measure;
import com.talent.measure.model.MeasurePrintBill;
import com.talent.measure.model.PrintModel;
import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@SuppressWarnings("unchecked")
@Controller
public class PrintController extends BaseController {

	@Autowired
	private MeasureDao measureDao;

	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private MeasureTaskReportDao measureTaskReportDao;
	
	@Autowired
    private BusinessConfigDao bcDao;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	
	
	@RequestMapping(value = "/PrintController/rtPrintModelData.do")
	public void printModelData(String jsonParams, ModelMap model, HttpServletResponse response) {
		
		/*Message message = new Message();*/
		String result =""; 
		try {
			jsonParams = jsonParams.substring(1);
			jsonParams = jsonParams.substring(0, jsonParams.length() - 1);
			MeasurePrintBill mtd = objectMapper.readValue(jsonParams, MeasurePrintBill.class);
			// 根据物流号获取到所有的计量信息……
			List<Measure> m = measureDao.getPrintDataInfos(mtd.getMatchid());
			if (null !=m && m.size() == 1) {
				Measure mt = m.get(0);
				mt.setUsermemo((null == mt.getUsermemo() ? "" : mt.getUsermemo()) + mtd.getPrinttype());
				mt.setSysmemo(mtd.getEquname());//打印单据的衡器，用备用字段传值
				if(null != mt.getMaterialspec() && !"".equals(mt.getMaterialspec())){
					mt.setMaterialspec("(" + mt.getMaterialspec() + ")");
				}
				
				//根据业务类型查找模板表获取到对应的模板
				List<PrintModel> pm = measureDao.getPrintModelInfos(mt.getOperatype());
				if (null !=pm && pm.size() > 0) {
					PrintModel lastPm = null;
					String tareTime = mt.getTaretime();// 皮重时间
					String grossTime = mt.getGrosstime();// 毛重时间
					String suttleTime = mt.getSuttletime();// 净重时间
					for (int i = 0; i < pm.size(); i++) {
						PrintModel pmInfo = pm.get(i);
						String mType = pmInfo.getMeasuretype();
						if(null != suttleTime){
							if("S".equals(mType)){
								mt.setPrintModelType("S");
								lastPm = pmInfo;
								break;
							}
						}else{
							if("T".equals(mType) && null != tareTime){
								mt.setPrintModelType("T");
								lastPm = pmInfo;
								break;
							}
							else if("G".equals(mType) && null != grossTime) {
								mt.setPrintModelType("G");
								lastPm = pmInfo;
								break;
							}
						}
					}
					
					try{
						mtd.setPrintModelType(mt.getPrintModelType());
						int printnum=1;
						if("10".equals(mt.getOperatype())){//如果是厂内调拨，根据业务号查询打印次数
							 printnum = measureDao.queryPrintnum(mt.getTaskcode());
						}
						result = "<?xml version='1.0' encoding='UTF-8'?><ArrayOfBill>" ;
						for(int i=0;i<printnum;i++){
							result = result +lastPm.getBillxml();
						}	
						result = result +"</ArrayOfBill>";
						Document printDoc = DocumentHelper.parseText(result);
						List<Element> paramList = printDoc.selectNodes("//param");
						for(Element replaceParam : paramList){
							String paramName = replaceParam.getText();
							String paramValue = "";
							switch (paramName) {
								case "Title":
									paramValue = "磅单打印";
									break;
								case "ReDo":
									paramValue = "ReDo";
									break;
								case "printweigh":
									paramValue = mt.getSysmemo();
									break;
								case "printcount":
									try{
										paramValue = (measureTaskReportDao.getPrintCount(mtd) + 1) + "";
									}catch(Exception e){
										paramValue = "1";
									}
									break;
								case "printtime":
									paramValue = commonDao.sysdate("yyyy-MM-dd HH:mm:ss");
									break;
								default:
									try {
										paramValue = BeanUtils.getProperty(mt, paramName.toLowerCase());
										paramValue = (paramValue == null ? "" : fullWidth2halfWidth(paramValue));
									} catch (Exception e) {
										paramValue = "";
									}
									break;
							}
							result = result.replaceAll(paramName, paramValue);
						}
						measureTaskReportDao.savePrintBill(mtd);
					}catch(Exception e){
						result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ArrayOfBill><Bill Script=\"打印错误\">打印失败，原因：打印模板格式错误！</Bill></ArrayOfBill>";
					}
				}else {
					result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ArrayOfBill><Bill Script=\"打印错误\">打印失败，原因：没有找到打印模板！</Bill></ArrayOfBill>";
				}
			} else {
				result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ArrayOfBill><Bill Script=\"打印错误\">打印失败，原因：获取计量信息失败！</Bill></ArrayOfBill>";
			}
		} catch (Exception e) {
			result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ArrayOfBill><Bill Script=\"打印错误\">打印失败，原因：未知错误" + e.getMessage() + "</Bill></ArrayOfBill>";
		}
		
		/*message.setMsg(result);
		return message;*/
		try {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/PrintController/PrintPostModelData.do", method = RequestMethod.POST)
	public Message PrintPostModelData(@RequestBody String jsonParams, ModelMap model, HttpServletResponse response) {
		
		Message message = new Message();
		FunctionLog logs = new FunctionLog();
		logs.setFunctionname("PrintPostModelData.do");
		logs.setParaminfo("PrintPostModelData打印方法开始。。。。。。"+jsonParams);
		bcDao.insertfunctionlog(logs);
		String result = "";
		try {
			jsonParams = jsonParams.substring(1);
			jsonParams = jsonParams.substring(0, jsonParams.length() - 1);
			MeasurePrintBill mtd = objectMapper.readValue(jsonParams, MeasurePrintBill.class);
			// 根据物流号获取到所有的计量信息……
			List<Measure> m = measureDao.getPrintDataInfos(mtd.getMatchid());
			if (null !=m && m.size() == 1) {
				Measure mt = m.get(0);
				mt.setUsermemo((null == mt.getUsermemo() ? "" : mt.getUsermemo()) + mtd.getPrinttype());
				mt.setSysmemo(mtd.getEquname());//打印单据的衡器，用备用字段传值
				if(null != mt.getMaterialspec() && !"".equals(mt.getMaterialspec())){
					mt.setMaterialspec("(" + mt.getMaterialspec() + ")");
				}
				
				//根据业务类型查找模板表获取到对应的模板
				List<PrintModel> pm = measureDao.getPrintModelInfos(mt.getOperatype());
				if (null !=pm && pm.size() > 0) {
					PrintModel lastPm = null;
					String tareTime = mt.getTaretime();// 皮重时间
					String grossTime = mt.getGrosstime();// 毛重时间
					String suttleTime = mt.getSuttletime();// 净重时间
					for (int i = 0; i < pm.size(); i++) {
						PrintModel pmInfo = pm.get(i);
						String mType = pmInfo.getMeasuretype();
						if(null != suttleTime){
							if("S".equals(mType)){
								mt.setPrintModelType("S");
								lastPm = pmInfo;
								break;
							}
						}else{
							if("T".equals(mType) && null != tareTime){
								mt.setPrintModelType("T");
								lastPm = pmInfo;
								break;
							}
							else if("G".equals(mType) && null != grossTime) {
								mt.setPrintModelType("G");
								lastPm = pmInfo;
								break;
							}
						}
					}
					
					try{
						result = lastPm.getBillxml();
						Document printDoc = DocumentHelper.parseText(result);
						List<Element> paramList = printDoc.selectNodes("//param");
						for(Element replaceParam : paramList){
							String paramName = replaceParam.getText();
							String paramValue = "";
							switch (paramName) {
								case "Title":
									paramValue = "磅单打印";
									break;
								case "ReDo":
									paramValue = "ReDo";
									break;
								case "printweigh":
									paramValue = mt.getSysmemo();
									break;
								case "printcount":
									paramValue = "1";
									break;
								case "printtime":
									paramValue = commonDao.sysdate("yyyy-MM-dd HH:mm:ss");
									break;
								default:
									try {
										paramValue = BeanUtils.getProperty(mt, paramName.toLowerCase());
										paramValue = (paramValue == null ? "" : fullWidth2halfWidth(paramValue));
									} catch (Exception e) {
										paramValue = "";
									}
									break;
							}
							result = result.replaceAll(paramName, paramValue);
						}
						mtd.setPrintModelType(mt.getPrintModelType());
						measureTaskReportDao.savePrintBill(mtd);
					}catch(Exception e){
						result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Bill Script=\"打印错误\">打印失败，原因：打印模板格式错误！</Bill>";
					}
				}else {
					result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Bill Script=\"打印错误\">打印失败，原因：没有找到打印模板！</Bill>";
				}
			} else {
				result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Bill Script=\"打印错误\">打印失败，原因：获取计量信息失败！</Bill>";
			}
		} catch (Exception e) {
			result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Bill Script=\"打印错误\">打印失败，原因：未知错误" + e.getMessage() + "</Bill>";
		}
		
		/*try {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		message.setMsg(result);
		try{
			logs.setFunctionname("PrintPostModelData.do");
			logs.setParaminfo("PrintPostModelData打印方法结束。。。。。。"+result.substring(0,200));
			bcDao.insertfunctionlog(logs);
		}catch(Exception e){
			
		}
		return message;
	}
	
	//把全角的字符转换成半角，因为全角字符打印机有可能不认
	private String fullWidth2halfWidth(String fullWidthStr) {
        if (null == fullWidthStr || fullWidthStr.length() <= 0) {
            return "";
        }
        char[] charArray = fullWidthStr.toCharArray();
        //对全角字符转换的char数组遍历
        for (int i = 0; i < charArray.length; ++i) {
            int charIntValue = (int) charArray[i];
            //如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
            if (charIntValue >= 65281 && charIntValue <= 65374) {
                charArray[i] = (char) (charIntValue - 65248);
            } else if (charIntValue == 12288) {
                charArray[i] = (char) 32;
            }
        }
        return new String(charArray);
    }
}
