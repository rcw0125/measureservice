package com.talent.measure.web;

import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.base.util.BaseController;
import com.talent.measure.dao.BusinessConfigDao;
import com.talent.measure.model.Mointor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MointorController extends BaseController{
	
	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private BusinessConfigDao  businessDao;
	@ResponseBody
	@RequestMapping(value = "/systemointor/memory.do")
	public String queryMemory(ModelMap model,PageModel pm) {
		try{
			return "{\"success\":true,\"used\":"+500+"}";
		}catch(Exception e){
			return "{\"success\":true,\"used\":0}";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/systemointor/cpu.do")  
	public String queryCpu(ModelMap model,PageModel pm) {
		try{
			return "{\"success\":true,\"used\":"+500+"}";
		}catch(Exception e){
			return "{\"success\":true,\"used\":0}";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/systemointor/weight.do")  
	public Message mointorWeight(ModelMap model,PageModel pm) {
		Message message = new Message();
		try{
			message.setRows(platformDao.queryList(new Mointor()));
		}catch(Exception e){
			message.setSuccess(false);
			message.setMsg("获取衡器监控数据失败！");
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/systemointor/exception.do", method = RequestMethod.POST)  
	public Message weightException(@RequestBody Mointor mointor,ModelMap model) {
		Message message = new Message();
		try{
			platformDao.getNewID(mointor);
			platformDao.insert(mointor);
		}catch(Exception e){
			message.setSuccess(false);
			message.setMsg("获取衡器监控数据失败！");
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/systemointor/queryMointorinfo.do")
	public Message queryMeasureSum(Mointor m,PageModel pm) {
		Message msg = new Message();
		try {
			pm = businessDao.queryMointorinfo(m, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
}
