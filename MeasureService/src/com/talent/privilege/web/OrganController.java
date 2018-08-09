package com.talent.privilege.web;

import com.talent.base.dao.PlatformDao;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.base.util.BaseController;
import com.talent.privilege.model.Organ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrganController extends BaseController {

	@Autowired
	private PlatformDao platformDao;

	@ResponseBody
	@RequestMapping(value = "/organ/queryPage.do")  
	public Message queryPage(Organ organ,String search,ModelMap model,PageModel pm) {
		
		Message msg = new Message();
		try{
			organ.setOrganname(search);
			organ.setOrgancode(search);
			pm = platformDao.queryPage(organ, pm);
			msg.setTotal(pm.getAllcount());
			msg.setRows(pm.getList());
		}catch (Exception e){	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/organ/addoredit.do")  
	public Message addorEdit(Organ organ,ModelMap model) {
		
		Message msg = new Message();
		try{
			if(-1 == organ.getId()){
				platformDao.getNewID(organ);
				platformDao.insert(organ);
			}else{
				platformDao.update(organ);
			}
			msg.setTotal(organ.getId());
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/organ/del.do")  
	public Message del(Organ organ,String ids,ModelMap model) {
		
		Message msg = new Message();
		try{
			platformDao.remove(organ, ids);
		}catch (Exception e) {	
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/organ/loadform.do")  
	public Organ loadForm(Organ organ,ModelMap model) {
		
		try{
			if(-1 != organ.getId()){
				organ = platformDao.get(organ);
			}
			if(null == organ){
				organ = new Organ();
			}
		}catch (Exception e) {	
			organ = new Organ();
		}
		return organ; 
	}
}
