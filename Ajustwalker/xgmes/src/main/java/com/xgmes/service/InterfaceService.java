package com.xgmes.service;

import java.util.List;
import org.dom4j.Document;

import com.talent.core.model.Message;
import com.xgmes.model.Datatransfer;
import com.xgmes.model.Measure;

public interface InterfaceService {
	
	public Message InterfaceDataDecode(Document doc,String savedFileName,List<Datatransfer> datatransferList);
	
	public Message InterfaceDataFill(Measure measure,List<Datatransfer> datatransferList,String isormeasure);
	
}