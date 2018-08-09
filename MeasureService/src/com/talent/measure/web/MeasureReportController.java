package com.talent.measure.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.base.model.Message;
import com.talent.base.model.PageModel;
import com.talent.base.util.WebUtils;
import com.talent.measure.dao.MeasureReportDao;
import com.talent.measure.model.MeasureReport;
import com.talent.measure.model.PrintLog;
import com.talent.measure.model.TareLog;


@Controller
public class MeasureReportController {
   

	@Autowired
	private MeasureReportDao mreportDao;
	
	// 查询可以修改的信息列表
	@ResponseBody
	@RequestMapping(value = "/measureReport/queryReportDetail.do")
	public Message queryReportDetail(MeasureReport m,String carno, PageModel pm) {
		Message msg = new Message();
		try {
			if("4".equals(m.getRecordtype())){
				pm = mreportDao.queryReportException(m, pm);
			}else{
				pm = mreportDao.queryReportDetail(m, pm);	
			}
			
			msg.setTotal(pm.getAllcount());
			msg.setMore(pm.getSummary());
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/measureReport/queryMeasureSum.do")
	public Message queryMeasureSum(MeasureReport m,PageModel pm) {
		Message msg = new Message();
		try {
			pm = mreportDao.queryReportSum(m, pm);
			msg.setTotal(pm.getAllcount());
			msg.setMore(pm.getSummary());
			msg.setRows(pm.getList());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	public void makeDir(File dir) {  
        if(! dir.getParentFile().exists()) {  
            makeDir(dir.getParentFile());  
        }  
        dir.mkdir();  
    }
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/excel/generate.do")
	public String excelExport(String reportName,String reportUrl,String requestbody,long totalRows,String vcf,String vct,ModelMap model,HttpServletRequest request) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String fileName = reportName + "_" + System.currentTimeMillis() + ".xlsx";
			String filePath = File.separator + "talent" + File.separator + "temp" + File.separator + "excel";

			File p = new File(filePath);
			if(!p.exists() && !p.isDirectory()){       
				makeDir(p);
			}
			
			File f = new File(filePath + File.separator + fileName);
			f.createNewFile();
			OutputStream os = new FileOutputStream(f);
			Workbook wb = new SXSSFWorkbook(2500);
			
			CellStyle cellStyle = wb.createCellStyle(); 
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN); //下边框    
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);//上边框    
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);//右边框
			
			Font font = wb.createFont();    
			font.setFontName("仿宋_GB2312");     
			font.setFontHeightInPoints((short)10);//设置字体大小
			cellStyle.setFont(font);//选择需要用到的字体格式
			
			cellStyle.setWrapText(true);//设置自动换行
			
			Sheet sheet = wb.createSheet(reportName);
			int rows = 0;
			int cells = 0;
			Row row = null;
			Cell cell = null;
			
			//统计时间表头
			HashMap<String,Object> requestBodyObject = objectMapper.readValue(requestbody,HashMap.class);
			Object btime = requestBodyObject.get("begintime");
			Object etime = requestBodyObject.get("endtime");
			if((null != btime || null != etime) && (!"".equals(btime) || !"".equals(etime))){
				row = sheet.createRow(rows);
				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("统计时间 ：" + (null == btime ? "" : btime) + " 至    " + (null == etime ? "" : etime));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,5));
				rows++;
			}
			
			row = sheet.createRow(rows);
			String[] columnTitles = vct.split(",");
			String[] columnFields = vcf.split(",");
			for(int i=0;i<columnTitles.length;i++){
				cell = row.createCell(cells);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(columnTitles[i]);
				cells++;
			}
			rows++;
			
			String requestIp = request.getLocalAddr();
			int requestPort = request.getLocalPort();
			String contextPath = request.getContextPath();
			int pageSize = (Integer)requestBodyObject.get("limit");
			long pages = totalRows/pageSize;
			for(long i=0;i<=pages;i++){
				if(0 != i){
					requestbody = requestbody.replace("\"offset\":" + ((i-1)*pageSize) +",", "\"offset\":" + (i*pageSize) +",");
				}
				
				//调用datagrid连接，传入参数进行post提交
				Map<String,Object> responseBody = objectMapper.readValue(WebUtils.get("http://" + requestIp + ":" + requestPort + reportUrl, requestbody),HashMap.class);
				List<Map<String,Object>> lists = (List<Map<String,Object>>)responseBody.get("rows");
				for (int k = 0; k < lists.size(); k++) {
					row = sheet.createRow(rows);
					for (int j = 0; j < columnFields.length; j++) {
						String cellColumn = columnFields[j];
						cell = row.createCell(j);
						cell.setCellStyle(cellStyle);
						try {
							Object cellValue = lists.get(k).get(cellColumn);
							if(cellValue instanceof Integer){
								cell.setCellValue(Integer.parseInt(cellValue.toString()));
							}else if(cellValue instanceof Double){
								cell.setCellValue(Double.parseDouble(cellValue.toString()));
							}else if(cellValue instanceof Float){
								cell.setCellValue(Float.parseFloat(cellValue.toString()));
							}else{
								cell.setCellValue(cellValue.toString());
							}
						} catch (Exception e) {
							cell.setCellValue("");
						}
					}
					rows++;
				}
			}
			wb.write(os);
			os.close();
			wb.close();
			return "{\"success\":true,\"downloadurl\":\"http://" + requestIp + ":" + requestPort + contextPath + "/excel/download.do\",\"fileName\":\""+fileName+"\"}";
		} catch (Exception ex) {
			return "{\"success\":false}";
		}
	}
	
	@RequestMapping("/excel/download.do")
	public void download(String fileName, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("gb2312"), "ISO8859-1" ));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			String path = "";
			if(fileName.indexOf(".xlsx")>=0){
				path = File.separator + "talent" + File.separator + "temp" + File.separator + "excel" + File.separator + fileName;
			}else if(fileName.indexOf(".pdf")>=0){
				path = File.separator + "talent" + File.separator + "temp" + File.separator + "excel" + File.separator + fileName;
			}else{
				throw new FileNotFoundException("找不到文件夹或目录");
			}
			File file=new File(path); 
			OutputStream os = response.getOutputStream(); 
			response.reset();
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
			response.setContentType("application/octet-stream; charset=utf-8");  
	        os.write(FileUtils.readFileToByteArray(file));
	        os.flush(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param matchid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measureReport/queryPrintinfo.do")
	public List<PrintLog> queryPrintinfo(String matchid) {
		List<PrintLog>  list =null;
		try {
			  list= mreportDao.queryPrintinfo(matchid);
			
		} catch (Exception e) {
			list = new ArrayList<PrintLog>();
		}

		return list;
	}
	
	// 查询可以修改的信息列表
		@ResponseBody
		@RequestMapping(value = "/measureReport/queryReportLog.do")
		public Message queryReportLog(MeasureReport m,String carno, PageModel pm) {
			Message msg = new Message();
			try {
				pm = mreportDao.queryReportLog(m, pm) ;
				msg.setTotal(pm.getAllcount());
				msg.setMore(pm.getSummary());
				msg.setRows(pm.getList());
			} catch (Exception e) {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}

			return msg;
		}
	
		
		/**
		 * 皮重日志查询
		 * @param m
		 * @param carno
		 * @param pm
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/measureReport/queryTareinfo.do")
		public Message queryReportDetail(TareLog tlog,PageModel pm) {
			Message msg = new Message();
			try {
				if("1".equals(tlog.getTypes())){
					pm = mreportDao.queryTareinfo(tlog, pm);
				}else{
					pm = mreportDao.queryTareloginfo(tlog, pm) ;
				}
				msg.setTotal(pm.getAllcount());
				msg.setRows(pm.getList());
			} catch (Exception e) {
				msg.setSuccess(false);
				msg.setMsg("操作失败！");
			}

			return msg;
		}
		
}
