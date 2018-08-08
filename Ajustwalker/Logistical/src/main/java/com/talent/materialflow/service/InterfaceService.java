package com.talent.materialflow.service;

import java.util.List;
import org.dom4j.Document;

import com.talent.core.model.Message;
import com.talent.materialflow.model.Datatransfer;
import com.talent.materialflow.model.Measure;

public interface InterfaceService {
	
	public Message InterfaceDataDecode(Document doc,String savedFileName,List<Datatransfer> datatransferList);
	
	public Message InterfaceDataFill(Measure measure,List<Datatransfer> datatransferList,String isormeasure);
	
}