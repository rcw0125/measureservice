package com.talent.measure.web;

import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.base.util.XmlUtils;
import com.talent.measure.model.Equipment;
import com.talent.measure.model.EquipmentParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeighterConfigController extends BaseController {
	
	@Autowired
	private PlatformDao platformDao;
	
	@Autowired
	private XmlUtils xmlUtils;
	
	@RequestMapping(value = "/weighter/init")
	public String loadWeighters(ModelMap model) {
		Equipment equipment = new Equipment();
		List<Equipment> equipmentList = platformDao.queryList(equipment);
		model.addAttribute("weighterconfiglist", equipmentList);
		return "weighterconfig";
	}
	
	@ResponseBody
	@RequestMapping(value = "/weighter/info")
	public Map<String,String> loadWeighterInfo(EquipmentParam equipmentParam,String xps,ModelMap model) {
		Map<String,String> result = new HashMap<String,String>();
		equipmentParam = platformDao.get(equipmentParam);
		Document doc = xmlUtils.parseStringToXml(equipmentParam.getParaminfos());
		String[] xpsArry = xps.split(",");
		for(String xp : xpsArry){
			System.out.println(xp);
			Element e = xmlUtils.getElementByXpath(doc, xp);
			try{
				result.put(xp,e.attributeValue("value"));
			}catch(NullPointerException ee){
				result.put(xp,"");
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/weighter/save")
	public Object saveWeighterInfo(String xmlPath,String xmlValue,ModelMap model) {
		Message msg = new Message();
		
		return msg;
	}
}
