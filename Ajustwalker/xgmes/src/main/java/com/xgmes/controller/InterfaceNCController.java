package com.xgmes.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.xgmes.mapper.InterfaceNCMapper;
import com.xgmes.model.Applicationbill;

@Controller
public class InterfaceNCController extends BaseController {

	@Resource
	private InterfaceNCMapper interMapper;
 
	@RequestMapping(value = "/interface/Interfaceshow")
	public String makecardcg(ModelMap model) {
		return "Interfaceshow";
	}
	
	@ResponseBody
	@RequestMapping(value = "/interface/queryInterfaceList.do")
	public Message queryInterfaceList(Applicationbill app, ModelMap model, Pagination<Applicationbill> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(interMapper.queryInterfaceList(app));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
