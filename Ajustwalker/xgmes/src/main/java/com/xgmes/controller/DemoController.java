package com.xgmes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.core.controller.BaseController;
import com.talent.core.exception.PageNotFoundException;
import com.talent.core.log.annotation.Logger;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.platform.service.mapper.SchemaMapper;
import com.talent.core.privilege.model.User;
import com.talent.core.realtime.service.PushMessageUtil;
import com.talent.core.redis.BaseRedis;
import com.talent.core.util.HttpUtils;
import com.talent.core.util.ResourceUtils;
import com.talent.core.util.XmlUtils;
import com.xgmes.service.InterfaceService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class DemoController extends BaseController {
	
	@Autowired
	private HttpUtils httpUtils;
	
	@Autowired
	private XmlUtils xmlUtils;
	
	@Autowired
	private ResourceUtils resourceUtils;
	
	@Resource
	private InterfaceService interfaceService;
	
	@Resource
	private SchemaMapper schemaMapper;
	
	@Autowired
	private BaseRedis<Message> baseRedis;
	
	@Autowired
	private ObjectMapper jsonObjectMapper;
	
	@RequestMapping(value="/sendMsgToPerson")
	public void sendMsgToPerson(HttpServletRequest request,String sendToId,String content) {
		PushMessageUtil.sendMessageToOneCallBack("AAAAA","showMessage", "指定发送人信息不能为Null", "clickEvent");
		PushMessageUtil.sendMessageToOneCallBack("BBBBB","showMessage", "[BBBBB] 向你发送:[11111]", "clickEvent");
	}
	
	//@PreAuthorize("hasPermission('ROLE','tlh33')")
	@PreAuthorize("permitAll")
	@RequestMapping(value = "/test/getuser")
	public String getuser(ModelMap model) {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(u.getUsername());
		return "ironmointor";
	}
	
	@RequestMapping(value = "/demo")
	public void demo(ModelMap model,HttpServletResponse response) {
		try{
			Document doc = xmlUtils.parseFileToXml("E:/Downloads/NC接口/回传history/DH1608170016.xml");
			String xmlString = doc.asXML();
			String result =  httpUtils.post(resourceUtils.getResource("nc.interfaces.out.url"),"text/xml;charset=utf-8", "text/xml;charset=utf-8",xmlString,"GBK");
			try {
				response.setContentType("text/xml;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.write(result);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping("/upload")
	@PreAuthorize("hasRole('ROLE_USER')")
	public Message addUser(@RequestParam("file_data") CommonsMultipartFile[] files, HttpServletRequest request) {
		Message message = new Message(true,"上传文件成功");
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				try {
					FileOutputStream os = new FileOutputStream("E:/" + new Date().getTime() + files[i].getOriginalFilename());
					FileInputStream in = (FileInputStream) files[i].getInputStream();
					int b = 0;
					while ((b = in.read()) != -1) {
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
				} catch (Exception e) {
					message = new Message(true,"上传文件失败");
				}
			}
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/unauth/basedata/batchset.do")
	public Map<String,Object> materialdataflags(ModelMap model){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			File file = new File("E:/邢钢基础数据.xlsx");
			FileInputStream in = new FileInputStream(file);
			Workbook wb = null;
			if(file.getName().indexOf(".xlsx") > -1){
				wb = new XSSFWorkbook(in);
			}else{
				wb = new HSSFWorkbook(in);
			}
			Sheet sheet = wb.getSheetAt(0);
			for(int r=sheet.getFirstRowNum();r<=sheet.getLastRowNum();r++){
				Row row = sheet.getRow(r);
				String materialcode = row.getCell(9).getStringCellValue();
				int isormeasure = -1;
				if(1 == row.getCell(2).getNumericCellValue()){
					isormeasure = 1;
				}else if(0 == row.getCell(2).getNumericCellValue() && 1 == row.getCell(3).getNumericCellValue()){
					isormeasure = 0;
				}
				
				int isormonitor = -1;
				if(1 == (int) row.getCell(6).getNumericCellValue()){
					isormonitor = 1;
				}
				System.out.println("UPDATE B_MATERIAL_T T SET T.ISORMEASURE = " + isormeasure + ",T.ISORMONITOR = " + isormonitor + " WHERE T.MATERIALCODE = '" + materialcode + "';");
			}
			
			Sheet sheet2 = wb.getSheetAt(1);
			for(int r=sheet2.getFirstRowNum();r<=sheet2.getLastRowNum();r++){
				Row row = sheet2.getRow(r);
				String foldercode = row.getCell(6).getStringCellValue();
				int isormeasure = -1;
				if(1 == row.getCell(2).getNumericCellValue()){
					isormeasure = 1;
				}else if(0 == row.getCell(2).getNumericCellValue() && 1 == row.getCell(3).getNumericCellValue()){
					isormeasure = 0;
				}
				
				int isormonitor = -1;
				if(1 == (int) row.getCell(5).getNumericCellValue()){
					isormonitor = 1;
				}
				System.out.println("UPDATE B_FOLDER_T T SET T.ISORMEASURE = " + isormeasure + ",T.ISORMONITOR = " + isormonitor + " WHERE T.FOLDERCODE = '" + foldercode + "';");
			}
			wb.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/unauth/test/aaaaa/{roleId}")
	public Message test(@PathVariable("roleId") String roleId){
		Message msg = new Message();
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/unauth/test/bbbbb.do")
	public Message test2(){
		Message msg = new Message();
		long result = baseRedis.publish("bar", "bar");
		msg.setMsg(result + "");
		System.out.println(result);
		return msg;
	}
	
	@Logger(module = "火运接口",method="测试PageNotFoundException")
	@ResponseBody
	@RequestMapping(value = "/unauth/test/ddddd.do")
	public Message test(String arg,String arg2)throws PageNotFoundException {
		if(jsonObjectMapper == null){
			throw new PageNotFoundException("找不到页面");
		}else{
			
		}
		return new Message(true,arg + arg2);
	}
	
	@ResponseBody
	@RequestMapping(value = "/unauth/test/dsql.do")
	public Message dsql(Pagination<Map<String,String>> page){
		return buildGridData(schemaMapper.querySchemaDynamicSql("select sysdate from dual"));
	}
}
