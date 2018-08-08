package com.talent.report.controller;

import com.alibaba.druid.util.StringUtils;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.util.ResourceUtils;
import com.talent.report.model.Printlog;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrintlogController extends BaseController {

	@Autowired
	private ResourceUtils resourceUtils;
	
	@RequestMapping(value = "/printlog")  
	public String init(ModelMap model) {
		return "measuredetail/printlog";
	}
	
	@ResponseBody
	@RequestMapping(value = "/printlog/list")  
	public Message queryPage(Printlog printlog,ModelMap model) {
		Message msg = new Message();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String filepath = resourceUtils.getResource("pdf.export.path");
			List<File> files = (List<File>)FileUtils.listFiles(new File(filepath), null, true);
			List<Printlog> result = new ArrayList<Printlog>();
			for(File f : files){
				String fn = f.getName().replaceAll(filepath, "");
				String[] properties = fn.split("_");
				Printlog pl = new Printlog();
				pl.setLogfilename(fn);
				if(!StringUtils.isEmpty(printlog.getExportman()) && properties[0].indexOf(printlog.getExportman()) == -1){
					continue;
				}
				if(!StringUtils.isEmpty(printlog.getLogfilename()) && fn.indexOf(printlog.getLogfilename()) == -1){
					continue;
				}
				pl.setExportman(properties[0]);
				Date exporttime = new Date(Long.parseLong(properties[1].replaceAll(".pdf", "")));
				if(!StringUtils.isEmpty(printlog.getBegintime())){
					if(sdf.parse(printlog.getBegintime()).after(exporttime)){
						continue;
					}
				}
				if(!StringUtils.isEmpty(printlog.getEndtime())){
					if(sdf.parse(printlog.getEndtime()).before(exporttime)){
						continue;
					}
				}
				pl.setExporttime(sdf.format(exporttime));
				result.add(pl);
			}
			msg.setTotal(result.size());
			msg.setRows(result);
			return msg;
		}catch (Exception e){
			return new Message(false,"操作失败！");
		}
	}
}
