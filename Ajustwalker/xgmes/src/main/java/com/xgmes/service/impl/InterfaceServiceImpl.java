package com.xgmes.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.hibernate.cache.ehcache.management.impl.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.talent.core.model.Message;
import com.talent.core.util.ResourceUtils;
import com.talent.core.util.XmlUtils;
import com.xgmes.mapper.BCommonMapper;
import com.xgmes.model.Datatransfer;
import com.xgmes.model.DatatransferItem;
import com.xgmes.model.InterfaceInfo;
import com.xgmes.model.Measure;
import com.xgmes.service.DatatransferService;
import com.xgmes.service.InterfaceInfoService;
import com.xgmes.service.InterfaceService;

@Service
@Transactional
public class InterfaceServiceImpl implements InterfaceService {
	
	@Autowired
	private DatatransferService datatransferService;
	
	@Autowired
	private XmlUtils xmlUtils;
	
	@Autowired
	private InterfaceInfoService interfaceInfoService;
	
	@Autowired
	private BCommonMapper commonMapper;
	
	@Autowired
	private ResourceUtils resourceUtils;
	
	@SuppressWarnings("unchecked")
	@Override
	public Message InterfaceDataDecode(Document xmlDoc,String savedFileName,List<Datatransfer> datatransferList) {
		
		Message message = new Message(true,"接口传输成功！");
		StringBuffer columns = new StringBuffer(30);
		StringBuffer values = new StringBuffer(30);
		int doctype = 0;
		try {
			//开始读取XML解析接口文件
			for(Datatransfer datatransfer : datatransferList){
				if(canUseTemplete(savedFileName,datatransfer.getIcode()) && "in".equals(datatransfer.getInorout())){
					columns.append(",").append("TYPEFLAG");
					doctype = savedFileName.indexOf("DH") > -1 ? 1 : 2;
					values.append(",").append("" + doctype + "");
					
					columns.append(",").append("IFILEPATH");
					values.append(",").append("'" + savedFileName + "'");
					
					Object dhid = datatransferService.queryBySql("select L_INTERFACEINFO_S.Nextval as id from dual").get(0);
					columns.append(",").append("ID");
					values.append(",").append(dhid.toString());
					
					try{
						String mainid = xmlDoc.selectSingleNode(datatransfer.getMaintabledata() + "/" + datatransfer.getIdpath()).getText();
						columns.append(",").append(datatransfer.getIdfield());
						values.append(",").append("'" + mainid + "'");
					}catch(Exception e){
						System.out.println("获取接口文件字段错误，取消保存！");
						continue;
					}
					
					for(DatatransferItem datatransferItem : datatransfer.getDatatransferItems()){
						if("main".equals(datatransferItem.getDatatable())){
							columns.append(",").append(datatransferItem.getDbcolumn());
							Element xmlEle = xmlUtils.getElementByXpath(xmlDoc,datatransfer.getMaintabledata() + "/" + datatransferItem.getIcolumn());
							if(null == xmlEle){
								System.out.println("获取接口文件字段错误，自动赋值为空！");
								values.append(",").append("''");
							}else{
								values.append(",").append("'" + xmlEle.getText() + "'");
							}
						}
					}
					datatransferService.updateSql("insert into " + datatransfer.getMaintable() + "(" + columns.substring(1) + ") values(" + values.substring(1) + ")");
					
					List<Node> bodyNodes = xmlUtils.getElementByXpath(xmlDoc,datatransfer.getItemtabledata()).selectNodes("child::*");
					for(Node bn : bodyNodes){
						columns = new StringBuffer(30);
						values = new StringBuffer(30);
						columns.append(",").append("FID");
						values.append(",").append(dhid.toString());
						Object dbid = datatransferService.queryBySql("select L_INTERFACEINFO_S.Nextval as id from dual").get(0);
						columns.append(",").append("ID");
						values.append(",").append(dbid.toString());
						try{
							String saleitemid = bn.selectSingleNode(datatransfer.getSubidpath()).getText();
							columns.append(",").append(datatransfer.getSubidfield());
							values.append(",").append("'" + saleitemid + "'");
						}catch(Exception e){
							System.out.println("获取接口文件字段错误，取消保存！");
							continue;
						}
						String materialcode = "";
						for(DatatransferItem datatransferItem : datatransfer.getDatatransferItems()){
							if("item".equals(datatransferItem.getDatatable())){
								columns.append(",").append(datatransferItem.getDbcolumn());
								if("vinvcode".equals(datatransferItem.getIcolumn())){
									materialcode = bn.selectSingleNode(datatransferItem.getIcolumn()).getText();
								}
								if("vinvname".equals(datatransferItem.getIcolumn()) && "501310102100".equals(materialcode)){
									values.append(",").append("'中鉻合金铸球'");
								}else{
									values.append(",").append("'" + bn.selectSingleNode(datatransferItem.getIcolumn()).getText() + "'");
								}
							}
						}
						datatransferService.updateSql("insert into " + datatransfer.getItemtable() + "(" + columns.substring(1) + ") values(" + values.substring(1) + ")");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.setMsg("接口传输失败");
		}
		return message;
	}
	
	private boolean canUseTemplete(String filename,String templetecode){
		String[] templetes = templetecode.split(",");
		boolean result = false;
		for(String templete : templetes){
			if(filename.indexOf(templete) > -1){
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public Message InterfaceDataFill(Measure measure,List<Datatransfer> datatransferList,String isormeasure) {
		Message message = new Message(true,"接口传输成功！");
		try {
			if("1".equals(isormeasure)){
				measure = commonMapper.queryMeasureDetail(measure.getMatchid()).get(0);
				List<InterfaceInfo> iilist = interfaceInfoService.queryByJpql("from InterfaceInfo t where t.planid = '" + measure.getPlanid() + "'");
				if(1 == iilist.size()){
					String iFilePath = iilist.get(0).getIfilepath();
					Node node = null;
					for(Datatransfer datatransfer : datatransferList){
						if(canUseTemplete(iFilePath,datatransfer.getIcode()) && "out".equals(datatransfer.getInorout())){
							Document xmlDoc = null;
							if(iFilePath.indexOf("qiyunhistory") > 0){
								xmlDoc = xmlUtils.parseFileToXml(iFilePath);
							}else{
								FileInputStream in = new FileInputStream(new File(iFilePath));
								SAXReader reader = new SAXReader();
								reader.setEncoding("GB2312");
								xmlDoc = reader.read(in);
							}
							for(DatatransferItem datatransferItem : datatransfer.getDatatransferItems()){
								if("main".equals(datatransferItem.getDatatable())){
									try{
										xmlDoc.selectSingleNode(datatransfer.getMaintabledata() + "/" + datatransferItem.getIcolumn()).setText(BeanUtils.getBeanProperty(measure, datatransferItem.getDbcolumn()).toString());
									}catch(Exception e){
										xmlDoc.selectSingleNode(datatransfer.getMaintabledata() + "/" + datatransferItem.getIcolumn()).setText("");
									}
								}else{
									node = xmlDoc.selectSingleNode("//" + datatransfer.getSubidpath()+"[text()[contains(.,'" + BeanUtils.getBeanProperty(measure,datatransfer.getSubidfield()) + "')]]/parent::*/" + datatransferItem.getIcolumn());
									if(null != node){
										String value = "";
										if("grossdate".equals(datatransferItem.getDbcolumn())){
											value = BeanUtils.getBeanProperty(measure,"grosstime").toString().substring(0,10);
										}else if("grosstime".equals(datatransferItem.getDbcolumn())){
											value = BeanUtils.getBeanProperty(measure,"grosstime").toString().substring(11);
										}else if("taredate".equals(datatransferItem.getDbcolumn())){
											value = BeanUtils.getBeanProperty(measure,"taretime").toString().substring(0,10);
										}else if("taretime".equals(datatransferItem.getDbcolumn())){
											value = BeanUtils.getBeanProperty(measure,"taretime").toString().substring(11);
										}else{
											value = BeanUtils.getBeanProperty(measure, datatransferItem.getDbcolumn()).toString();
										}
										node.setText(value);
									}
								}
							}
							
							if(null != node && !"DM".equals(datatransfer.getIcode())){
								Node n = (Node) node.getParent().clone();
								n.selectSingleNode(datatransfer.getSubidpath()).setText("");
								node.getParent().getParent().add(n);
							}
							
							String savedFileName = resourceUtils.getResource("nc.interfaces.out.path") + new File(iFilePath).getName();
							java.io.OutputStream out=new java.io.FileOutputStream(savedFileName);
							java.io.Writer wr = new java.io.OutputStreamWriter(out,"UTF-8");  
							xmlDoc.write(wr);
							wr.close();
							out.close();
							try{
								String xmlString;
								if(iFilePath.indexOf("qiyunhistory") > 0){
									xmlString = xmlDoc.asXML();
								}else{
									FileInputStream in = new FileInputStream(new File(savedFileName));
									SAXReader reader = new SAXReader();
									reader.setEncoding("UTF-8");
									xmlString = reader.read(in).asXML();
								}								
								String result =  post(resourceUtils.getResource("nc.interfaces.out.url"),"text/xml;charset=utf-8", "text/xml;charset=utf-8",xmlString,"UTF-8");
								message.setMsg(result);
								if(null == result || result.indexOf("<?xml version=\"1.0\" encoding='UTF-8'?>") == -1){
									message.setSuccess(false);
								}
							}catch(Exception e){
								message.setSuccess(false);
								message.setMsg(e.getMessage());
							}
						}
					}
				}else{
					message.setSuccess(false);
					message.setMsg("数据解析模板不存在或重复，请检查配置！");
					System.out.println("数据解析模板不存在或重复，请检查配置！");
				}
			}else{
				List<Map<String,Object>> datalist = commonMapper.queryStoreDetail(measure.getMatchid());
				if(datalist.size() > 0){
					String xmlPath = datalist.get(0).get("IFILEPATH").toString();
					Document xmlDoc = xmlUtils.parseFileToXml(xmlPath);
					for(int i=0;i<datalist.size();i++){
						Node node = null;
						Map<String,Object> data = datalist.get(i);
						if(!xmlPath.equals(datalist.get(i).get("IFILEPATH").toString())){
							String savedFileName = resourceUtils.getResource("nc.interfaces.out.path") + new File(xmlPath).getName();
							java.io.OutputStream out=new java.io.FileOutputStream(savedFileName);
							java.io.Writer wr=new java.io.OutputStreamWriter(out,"UTF-8");  
							xmlDoc.write(wr);
							
							try{
								String xmlString = xmlDoc.asXML();
								String result =  post(resourceUtils.getResource("nc.interfaces.out.url"),"text/xml;charset=utf-8", "text/xml;charset=utf-8",xmlString,"UTF-8");
								message.setMsg(result);
							}catch(Exception e){
								message.setSuccess(false);
								message.setMsg(e.getMessage());
							}
							wr.close();
							out.close();
							xmlDoc = xmlUtils.parseFileToXml(datalist.get(i).get("IFILEPATH").toString());
							xmlPath = datalist.get(i).get("IFILEPATH").toString();
						}
						for(Datatransfer datatransfer : datatransferList){
							if(canUseTemplete(data.get("IFILEPATH").toString(),datatransfer.getIcode()) && "out".equals(datatransfer.getInorout())){
								node = xmlDoc.selectSingleNode("//" + datatransfer.getSubidpath()+"[text()[contains(.,'" + data.get("SALEITEMID") + "')]]/parent::*/narrvnum");
								node.setText(data.get("SUTTLE") + "");
								node = xmlDoc.selectSingleNode("//" + datatransfer.getSubidpath()+"[text()[contains(.,'" + data.get("SALEITEMID") + "')]]/parent::*/vdef7");
								node.setText(data.get("MATCHID") + "");
								
								if(null != node && !"DM".equals(datatransfer.getIcode())){
									Node n = (Node) node.getParent().clone();
									n.selectSingleNode(datatransfer.getSubidpath()).setText("");
									node.getParent().getParent().add(n);
								}
							}
						}
					}
					
					String savedFileName = resourceUtils.getResource("nc.interfaces.out.path") + new File(xmlPath).getName();
					java.io.OutputStream out=new java.io.FileOutputStream(savedFileName);
					java.io.Writer wr=new java.io.OutputStreamWriter(out,"UTF-8");  
					xmlDoc.write(wr);
					
					try{
						String xmlString = xmlDoc.asXML();
						String result =  post(resourceUtils.getResource("nc.interfaces.out.url"),"text/xml;charset=utf-8", "text/xml;charset=utf-8",xmlString,"UTF-8");
						message.setMsg(result);
						if(null == result || result.indexOf("<?xml version=\"1.0\" encoding='UTF-8'?>") == -1){
							message.setSuccess(false);
						}
					}catch(Exception e){
						message.setSuccess(false);
						message.setMsg(e.getMessage());
					}
					wr.close();
					out.close();
				}
			}
		} catch (Exception e) {
			message.setSuccess(false);
			message.setMsg(e.getMessage());
		}
		return message;
	}
	
	private String post(String url,String acceptType,String contentType,String requestbody,String responseEncoding) {
		BufferedReader reader = null;
		String result = "";
		try {
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)u.openConnection();
			connection.setDoOutput(true);  
			connection.setDoInput(true);  
			connection.setRequestMethod("POST");  
			connection.setUseCaches(false);  
			connection.setConnectTimeout(180000);
			connection.setInstanceFollowRedirects(true);  
			connection.setRequestProperty("Accept",acceptType);
			connection.setRequestProperty("Content-Type",contentType);
			connection.setChunkedStreamingMode(5);  
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			out.append(requestbody);
			out.flush();  
			out.close();

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),responseEncoding),81920);
			String lines = "";
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null){
				sb.append(lines);
			}
			reader.close();
			connection.disconnect();
			result = sb.toString();
		} catch (Exception e) {
			result = null;
		}
		finally {  
			try {  
				if (reader != null) {  
					reader.close();  
				}  
			}catch (IOException ex) {  
				result = null;
			}  
		}  
		return result;
	}
}