package com.talent.materialflow.controller;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.talent.materialflow.model.Documenttype;
import com.talent.materialflow.service.BCommonService;
import com.talent.materialflow.service.mapper.DocumentTypeMapper;

@Controller
public class DocumentTypeController extends BaseController {

	@Resource
	private DocumentTypeMapper dtypeMapper;
	@Resource
	private BCommonService bcommonService;
	
	/**
	 * 单据类型
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/documenttype/documenttype")
	public String documenttype(ModelMap model) {
		model.addAttribute("unitcode", "10326");
		model.addAttribute("unitname", "废副产品库");
		return "documenttype";
	}

	@ResponseBody
	@RequestMapping(value = "/documenttype/queryPage.do")
	public Message queryPage(Documenttype dtype, ModelMap model, Pagination<Documenttype> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(dtypeMapper.queryList(dtype));
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/documenttype/queryInfoByid.do")
	public Documenttype queryApplicationbill(Documenttype dtype) {
		Documenttype tc = new Documenttype();
		if (-1!=dtype.getId()) {
			tc = dtypeMapper.queryInfoByid(dtype);
		}
		return tc;
	}

	/**
	 * 添加单据类型信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/documenttype/insertDocumenttype.do")
	public Message insertDocumenttype(Documenttype dtype) {
		Message msg = new Message();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			int i = dtypeMapper.queryCount(dtype);
			int j = dtypeMapper.queryCountById(dtype);
			if (dtype.getId()==-1) {
				if (i == 0) {
					dtype.setCreator(user.getDisplayname());
					String documentcode = bcommonService.getDocumenttype();
					dtype.setDocumentcode(documentcode);
					dtypeMapper.insertDocumenttype(dtype);
				} else {
					msg.setSuccess(false);
					msg.setMsg("单据类型已经存在！");
				}
			} else {
				if (j > 0) {
					msg.setSuccess(false);
					msg.setMsg("单据类型已经存在！");
				} else {
					dtype.setUpdater(user.getDisplayname());
					dtypeMapper.updateDocumenttype(dtype);
				}
			}

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/**
	 * 作废单据类型信息
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/documenttype/cancelDtype.do")
	public Message updateTaskcode(Documenttype dtype) {
		Message msg = new Message();
		try {
			dtype.setCanceler("作废人");
			dtypeMapper.cancelDocumenttype(dtype);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

}
