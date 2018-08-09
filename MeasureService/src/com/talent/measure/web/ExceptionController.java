package com.talent.measure.web;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.base.util.BaseController;
import com.talent.measure.dao.ExceptionDao;
import com.talent.measure.model.Exceptions;
import com.talent.measure.model.MeasurePhoto;
import com.talent.measure.model.Operaconfig;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionController extends BaseController {

	@Autowired
	private ExceptionDao exceptionDao;

	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/measure/queryException.do")
	public Message getmaterialname(Exceptions m, PageModel pm) {
		Message msg = new Message();
		try {
			pm = exceptionDao.queryException(m, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}

	// 根据物流号查询修改的数据信息
	@ResponseBody
	@RequestMapping(value = "/measure/queryInfo.do")
	public Exceptions queryInfo(String applicationno) {

		Message msg = new Message();
		Exceptions measure = new Exceptions();
		try {
			measure = exceptionDao.queryInfo(applicationno);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return measure;
	}

	/**
	 * 计量异常保存
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws DataAccessException
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/saveException.do")
	public Message saveException(@CookieValue(value="username",defaultValue="")String username,Exceptions exception) {

		exception.setCreateman(username); 
		Message msg = new Message();
		try {
			exception = exceptionDao.saveException(exception);

			if (exception.getFlag() == 0) {
				msg.setSuccess(false);
				msg.setMsg("操作失败");
			} else {
				msg.setSuccess(true);
				msg.setMsg("操作成功");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/measure/queryMeasureweighinfo.do")
	public Message queryMeasureweighinfo(Exceptions m, PageModel pm) {
		Message msg = new Message();
		try {
			pm = exceptionDao.queryMeasureweighinfo(m, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/measure/cancelMeasureweigh.do")
	public Message cancelMeasureweigh(@CookieValue(value="username",defaultValue="")String username,Exceptions exception) {
		exception.setCreateman(username);   
		Message msg = new Message();
		try {
			int i = exceptionDao.cancelMeasureweigh(exception);
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
	@RequestMapping(value = "/measure/insertMeasureweigh.do")
	public Message insertMeasureweigh(@CookieValue(value="username",defaultValue="")String username,Exceptions exception) {

		Message msg = new Message();
		Exceptions ec = new Exceptions();
		int i = 0;
		String sql = "";
//		int type = 0;
		try {

			/*if (0 == exception.getId()) {
				type = 1;

			} else {
				type = 2;
			}*/
			exception.setCreateman(username); 
			String[] weighid = exception.getTareweighid().split(",");
			if (weighid.length > 1) {
				for (int m = 0; m < weighid.length; m++) {
					if (m == 0) {
						sql = "\'" + weighid[m] + "\',";
					} else if (m == weighid.length - 1) {
						sql = sql + "\'" + weighid[m] + "\'";
					} else {
						sql = sql + "\'" + weighid[m] + "\',";
					}
				}
				System.out.println("输出sql语句.........." + sql);
				exception.setSql(sql);
				List<Exceptions> list = exceptionDao.queryEqumentByid(exception);
				for (int f = 0; f < list.size(); f++) {
					ec = (Exceptions) list.get(f);
					exception.setTareweighid(ec.getTareweighid());
					exception.setTareweigh(ec.getTareweigh());

//					if (type == 1) {
						i = exceptionDao.insertMeasureweigh(exception);
					/*} else if (type == 2) {
						i = exceptionDao.updateMeasureweigh(exception);
					}*/

				}
			} else {
				
					i = exceptionDao.insertMeasureweigh(exception);

			
			}

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
	@RequestMapping(value = "/measure/updateMeasureweigh.do")
	public Message updateMeasureweigh(@CookieValue(value="username",defaultValue="")String username,Exceptions exception) {

		Message msg = new Message();

		int i = 0;

		try {
			exception.setCreateman(username); 
			i = exceptionDao.updateMeasureweigh(exception);

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

	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/measure/queryMweighinfoByid.do")
	public Exceptions queryMweighinfoByid(String id) {
		Message msg = new Message();
		Exceptions measure = new Exceptions();
	/*	if (!"-1".equals(id)) {*/
			try {

				measure = exceptionDao.queryMweighinfoByid(id);
			} catch (Exception e) {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}
		/*} else {
			measure = new Exceptions();
			// measure.setMtype("T");
			measure.setMaterialcode("-1");
		}*/

		return measure;
	}

	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/measure/queryMweighinfosave.do")
	public Exceptions queryMweighinfosave(String id) {
		Exceptions measure = new Exceptions();
		measure.setMaterialcode("-1");
		return measure;
	}

	/*
	 * 业务类型查询
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/queryOperatype.do")
	public Message queryOperatype(ModelMap model) {
		Message msg = new Message();
		List<MeasurePhoto> list = new ArrayList<MeasurePhoto>();
		try {
			list = exceptionDao.queryOperatype();
		} catch (Exception e) {
		}
		msg.setRows(list);
		return msg;
	}

	/*
	 * 衡器查询
	 */
	@ResponseBody
	@RequestMapping(value = "/measure/queryEquipment.do")
	public Message queryEquipment(ModelMap model) {
		Message msg = new Message();
		List<MeasurePhoto> list = new ArrayList<MeasurePhoto>();
		try {
			list = exceptionDao.queryEquipment();
		} catch (Exception e) {
		}
		msg.setRows(list);
		return msg;
	}

	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/measure/queryOperaconfig.do")
	public Operaconfig queryOperaconfig(String operatype, PageModel pm) {
		Operaconfig oper = new Operaconfig();
		oper = exceptionDao.queryOperaconfig(operatype);
		return oper;
	}

	@ResponseBody
	@RequestMapping(value = "/measure/updateOperaconfig.do")
	public Message updateOperaconfig(Operaconfig o) {

		Message msg = new Message();
		try {
			int i = exceptionDao.updateOperaconfig(o);
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
}
