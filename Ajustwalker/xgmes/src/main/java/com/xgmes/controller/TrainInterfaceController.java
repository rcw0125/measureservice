package com.xgmes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.xgmes.service.DatatransferService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrainInterfaceController extends BaseController {
	
	@Autowired
	private ObjectMapper jsonObjectMapper;
	
	@Autowired
	private DatatransferService datatransferService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/unauth/trainmeasure/status.do", method = RequestMethod.POST)
	public Message trainBaseData(@RequestBody String data) {
		try {
			try{
				datatransferService.updateSql("declare clobValue B_TRAINSTATUS_LOG_T.Msg%TYPE;begin clobValue \\:='" + data + "'; insert into B_TRAINSTATUS_LOG_T (msg) values (clobValue);end;");
			}
			catch(Exception e){
				e.printStackTrace();
				datatransferService.updateSql("insert into B_TRAINSTATUS_LOG_T (msg) values ('" + e + "')");
			}
			List result = jsonObjectMapper.readValue(data, List.class);
			int success_count = 0;
			int failure_count = 0;
			for(Object o : result){
				String insertColumns = "";
				String insertValues = "";
				Map<String,Object> trainBaseData = (Map<String,Object>)o;
				Iterator<String> it = trainBaseData.keySet().iterator();
				while(it.hasNext()){
					String column = it.next();
					insertColumns = insertColumns + "," + column;
					insertValues = insertValues + ",'" + trainBaseData.get(column) + "'";
				}
				try{
					datatransferService.updateSql("delete from B_TRAINSTATUS_T where vreceivecode = '" + trainBaseData.get("vreceivecode") + "'");
					success_count = success_count + datatransferService.updateSql("insert into B_TRAINSTATUS_T(" + insertColumns.substring(1) + ") values (" + insertValues.substring(1) + ")");
				}catch(Exception e){
						failure_count++;
						e.printStackTrace();
				}
			}
			return new Message(true,"操作完毕，成功（" + success_count + "）条，失败（" + failure_count + "）条。");
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(false,Message.FAILURE_MESSAGE + e.getMessage());
		}
	}
}