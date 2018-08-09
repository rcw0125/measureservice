package com.talent.measure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.base.util.BaseController;
import com.talent.measure.common.BusinessConfig;
import com.talent.measure.dao.MsameweightDao;
import com.talent.measure.model.Measure;
import com.talent.measure.model.Msameweight;

@Controller
public class MsameweightController extends BaseController {

	@Autowired
	private MsameweightDao msweightDao;
	
	@Autowired
	private BusinessConfig businessConfig;

	/*
	 * 相似重量操作
	 */
	@ResponseBody
	@RequestMapping(value = "/msweight/insertConfigmodel.do", method = RequestMethod.POST)
	public Message insertConfigmodel(@RequestBody Msameweight ms) {
	
		Message msg = new Message();
		int i = 0;
		try {
			i = msweightDao.insertConfigmodel(ms);
			if (i > 0) {
				msg.setMsg("操作成功！");
			} else {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	@ResponseBody
	@RequestMapping(value = "/measureReport/querySameweight.do")
	public Message querySameweight(Msameweight m,String carno, PageModel pm) {
		Message msg = new Message();
		try {
			pm = msweightDao.querySameweight(m, pm);
			msg.setTotal(pm.getAllcount());;
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/measureReport/test.do")
	public void test(Measure measure) {
		try {
			businessConfig.checkOSflag(measure);
			//businessConfig.checkGrossTaredo(measure);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
