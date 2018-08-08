package com.xgmes.interfaces;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.talent.core.model.Message;
import com.xgmes.mapper.InterfaceNCMapper;
import com.xgmes.model.Applicationbill;
import com.xgmes.model.Datatransfer;
import com.xgmes.model.Measure;
import com.xgmes.service.DatatransferService;
import com.xgmes.service.InterfaceService;

@Component
public class UploadMointor {
	
	@Autowired
	private InterfaceNCMapper interfaceNCMapper;
	
	@Autowired
	private DatatransferService datatransferService;
	
	@Autowired
	private InterfaceService interfaceService;
	
	@Scheduled(fixedDelay=3600000,initialDelay=2000)//每5分钟执行一次，相当于只执行一次，延迟5秒执行。
    public void onStart() {
		try {
			System.out.println("数据上传接口开始.................");
			List<Datatransfer> datatransferList = datatransferService.findAll();
			List<Map<String,Object>> unUploadedList = interfaceNCMapper.queryUnuploadData();
			for(Map<String,Object> unupload : unUploadedList){
				Measure measure = new Measure();
				measure.setMatchid(unupload.get("MATCHID").toString());
				try{
					Message msg = interfaceService.InterfaceDataFill(measure,datatransferList,unupload.get("ISORMEASURE").toString());
					try{
						System.out.println(msg.getMsg());
						Applicationbill app = new Applicationbill();
						app.setMatchid(unupload.get("MATCHID").toString());
						app.setStatus(msg.getMsg());
						interfaceNCMapper.insertUploadLog(app);
					}catch(Exception e){
						e.printStackTrace();
					}
					if(msg.isSuccess() && (msg.getMsg().indexOf("successful=\"Y\"") > -1 || (msg.getMsg().indexOf("successful=\"N\"") > -1 && (msg.getMsg().indexOf("异常信息:已经导入过") > -1 || msg.getMsg().indexOf("不能重复提交单据") > -1)))){
						try{
							interfaceNCMapper.updateMeasureUpflag(unupload.get("MATCHID").toString());
							interfaceNCMapper.updateStoreinUpflag(unupload.get("MATCHID").toString());
						}catch(Exception e){
							System.out.println("数据上传保存状态异常.................");
						}
					}
				}catch(Exception e){
					System.out.println("数据上传接口异常.................");
				}
			}
			System.out.println("数据上传接口完成.................");
		} catch (Exception e) {
			System.out.println("数据上传接口异常.................");
		}
	}
}
