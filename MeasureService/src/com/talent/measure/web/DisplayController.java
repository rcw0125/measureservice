package com.talent.measure.web;

import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.util.BaseController;
import com.talent.measure.model.ConfigModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DisplayController extends BaseController{
	
	@Autowired
	private PlatformDao platformDao;
	
	@ResponseBody
	@RequestMapping(value = "/measuredisplay/queryList.do")  
	public List<ConfigModel> queryPage(ConfigModel configModel,ModelMap model) {
		
		List<ConfigModel> list = platformDao.queryList(configModel);
		if(0 == list.size()){
			String ot = configModel.getOperatype();
			if(null != ot && !"".equals(ot)){
				configModel.setOperatype("缺省");
				list = platformDao.queryList(configModel);
				for(ConfigModel cm : list){
					cm.setOperatype(ot);
					platformDao.insert(cm);
				}
			}			
			configModel.setOperatype(ot);
			list = platformDao.queryList(configModel);
		}		
		return list; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/measuredisplay/setctrlflag.do")  
	public Message ctrlFlag(ConfigModel configModel,ModelMap model){
		Message msg = new Message();
		try{
			platformDao.update(configModel);
		}catch(Exception e){
			msg.setSuccess(false);
			msg.setMsg("设置标志位失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/measuredisplay/editorder.do")  
	public Message editOrder(ConfigModel configModel,String direction,ModelMap model){
		Message msg = new Message();
		try{
			platformDao.order(configModel,"orderno",direction);
		}catch(Exception e){
			msg.setSuccess(false);
			msg.setMsg("设置标志位失败！");
		}
		return msg;
	}
}
