package com.xgmes.controller;

import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.util.ResourceUtils;
import com.xgmes.mapper.InterfaceNCMapper;
import com.xgmes.model.Applicationbill;
import com.xgmes.model.Datatransfer;
import com.xgmes.model.Measure;
import com.xgmes.service.DatatransferService;
import com.xgmes.service.InterfaceService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class InterfaceController extends BaseController {
	
	@Autowired
	private ResourceUtils resourceUtils;
	
	@Autowired
	private DatatransferService datatransferService;
	
	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private InterfaceNCMapper interfaceNCMapper;

	@ResponseBody
	@RequestMapping(value = "/unauth/interface/download")
	public void dataDownload(HttpServletRequest request,HttpServletResponse response){
		
		List<Datatransfer> datatransferList = datatransferService.findAll();
		
		String result = "success";
		try {
			InputStream in = request.getInputStream();
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(in);
			Element ide = (Element)doc.selectSingleNode("//ufinterface");
			String savedFileName = resourceUtils.getResource("http.interfaces.in.path") + ide.attribute("filename").getValue();
			
			java.io.OutputStream out=new java.io.FileOutputStream(savedFileName);
			java.io.Writer wr=new java.io.OutputStreamWriter(out,"UTF-8");  
			doc.write(wr);  
			wr.close();
			out.close();
            
			Message msg = interfaceService.InterfaceDataDecode(doc,savedFileName,datatransferList);
			if(!msg.isSuccess()){
				result = "传输失败";
			}
		} catch (Exception e) {
			result = "传输失败,错误：" + e.getMessage();
		}
		
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
	@RequestMapping(value = "/interface/queryUploadInfoList.do")  
	public Message queryPage(Applicationbill app,ModelMap model,Pagination<Map<String,String>> page) {
		try{
			return buildGridData(interfaceNCMapper.queryUploadData(app));
		}catch (Exception e){	
			return new Message(false,"操作失败！");
		}
	}
	
	@RequestMapping(value = "/interface/uploadstatus")
	public String uploadStatus(ModelMap model) {
		return "uploadstatus";
	}
	
	@ResponseBody
	@RequestMapping(value = "/unauth/interface/upload")
	public Message dataUpload(Measure measure,String isormeasure){
		Message result;
		if(null == isormeasure || isormeasure.length() == 0){
			result = interfaceService.InterfaceDataFill(measure,datatransferService.findAll(),"1");
		}else{
			result = interfaceService.InterfaceDataFill(measure,datatransferService.findAll(),"0");
		}
		Applicationbill app = new Applicationbill();
		app.setMatchid(measure.getMatchid());
		app.setStatus(result.getMsg());
		interfaceNCMapper.insertUploadLog(app);
		if(result.isSuccess() && (result.getMsg().indexOf("successful=\"N\"") > -1 || result.getMsg().indexOf("异常信息:已经导入过") > -1 || result.getMsg().indexOf("不能重复提交单据") > -1)){
			try{
				interfaceNCMapper.updateMeasureUpflag(measure.getMatchid());
				interfaceNCMapper.updateStoreinUpflag(measure.getMatchid());
			}catch(Exception e){
				System.out.println("数据上传保存状态异常.................");
			}
		}else{
			result.setSuccess(false);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/unauth/interface/byfile")
	public Message databyfile(@RequestParam("file_data") CommonsMultipartFile[] files,HttpServletRequest request,HttpServletResponse response){
		Message message = new Message(true,"上传文件成功");
		List<Datatransfer> datatransferList = datatransferService.findAll();
		
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				try {
					FileInputStream in = (FileInputStream) files[i].getInputStream();
					try {
						SAXReader saxReader = new SAXReader();
						Document doc = saxReader.read(in);
						Element ide = (Element)doc.selectSingleNode("//ufinterface");
						String savedFileName = resourceUtils.getResource("http.interfaces.in.path") + ide.attribute("filename").getValue();
						
						java.io.OutputStream out=new java.io.FileOutputStream(savedFileName);
						java.io.Writer wr=new java.io.OutputStreamWriter(out,"UTF-8");  
						doc.write(wr);  
						wr.close();
						out.close();
			            
						message = interfaceService.InterfaceDataDecode(doc,savedFileName,datatransferList);
					} catch (Exception e) {
						message = new Message(false,"传输失败,错误：" + e.getMessage());
					}
					in.close();
				} catch (Exception e) {
					message = new Message(true,"上传文件失败");
				}
			}
		}
		return message;
	}
	
	@RequestMapping(value = "/unauth/interface/interface")
	public String storein13(ModelMap model) {
		return "interface";
	}
}
