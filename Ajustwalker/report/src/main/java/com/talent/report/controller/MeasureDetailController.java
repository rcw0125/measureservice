package com.talent.report.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.talent.core.util.ResourceUtils;
import com.talent.report.model.MeasureDetail;
import com.talent.report.model.ReportLog;
import com.talent.report.model.ComboxData;
import com.talent.report.service.MeasureDetailService;
import com.talent.report.service.mapper.MeasureDetailMapper;

@Controller
public class MeasureDetailController extends BaseController {

	@Autowired
	private MeasureDetailMapper mDeatialMapper;

	@Resource
	private MeasureDetailService measureDetailService;

	@Autowired
	private ResourceUtils resourceUtils;

	/**
	 * 查询计量明细入口
	 * 
	 */
	@RequestMapping(value = "/measuredetail/visit.do")
	public String visit(ModelMap model, HttpServletRequest request) {
		model.addAttribute("opeatype", request.getParameter("opeatype"));
		model.addAttribute("titlename", request.getParameter("titlename"));
		return "measuredetail/measuredetail";
	}

	/**
	 * 查询计量明细报表
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/measuredetail/queryMeasureDetail.do")
	public Message queryPage(MeasureDetail mdetail, ModelMap model, Pagination<MeasureDetail> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(mDeatialMapper.queryList(mdetail));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/measuredetail/queryCount.do")
	public Message queryCount(MeasureDetail mdetail, ModelMap model, Pagination<MeasureDetail> page) {
		Message msg = new Message();
		try {
			MeasureDetail mr = mDeatialMapper.queryCount(mdetail);
			msg.setData(mr);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	/*
	 * 衡器查询
	 */
	@ResponseBody
	@RequestMapping(value = "/measuredetail/queryEquipment.do")
	public List<ComboxData> queryEquipment(ComboxData combox, ModelMap model) {
		return mDeatialMapper.queryEquipment(combox);
	}

	/*
	 * 衡器查询
	 */
	@ResponseBody
	@RequestMapping(value = "/measuredetail/queryEquipment1.do")
	public Message queryEquipment1(ComboxData combox, ModelMap model) {
		Message msg = new Message();
		List<ComboxData> list = new ArrayList<ComboxData>();
		try {
			list = mDeatialMapper.queryEquipment(combox);
		} catch (Exception e) {
		}
		msg.setRows(list);
		return msg;
	}

	/**
	 * 打印
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/measuredetail/printMeasureDetail.do")
	public Message print(MeasureDetail mdetail, String matchids, ModelMap model, HttpServletRequest request) {
		try {

			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			com.itextpdf.text.Font fontZH = new com.itextpdf.text.Font(bfChinese,8, com.itextpdf.text.Font.NORMAL);
			com.itextpdf.text.Font headFontZH = new com.itextpdf.text.Font(bfChinese,9, com.itextpdf.text.Font.BOLD);
			BaseColor headerColor = new BaseColor(212,212,212);
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			DecimalFormat df = new DecimalFormat("######0.00");
			Date printDate = Calendar.getInstance().getTime();

			String fileName = u.getDisplayname() + "_" + System.currentTimeMillis() + ".pdf";
			String filePath = resourceUtils.getResource("pdf.export.path") + fileName;

			FileOutputStream out = new FileOutputStream(filePath);
			Document document = new Document(PageSize.A4,5,5,5,5);
			PdfWriter.getInstance(document, out);
			document.open();

			String[] columns = new String[] { "sn", "planid", "matchid", "carno", "gross", "tare", "suttle",
					"grosstime", "taretime", "grossoperaname", "tareoperaname","printcount"};
			PdfPTable datatable = new PdfPTable(columns.length);
			float headerwidths[] = new float[] { 0.05f, 0.12f, 0.1f, 0.1f, 0.08f, 0.08f, 0.08f, 0.12f, 0.12f, 0.1f, 0.1f,0.05f};

			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100);
			datatable.getDefaultCell().setPadding(1);
			datatable.getDefaultCell().setBorderWidth(0.5f);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPHeaderCell fileheader = new PdfPHeaderCell();
			fileheader.addElement(new Paragraph(fileName, headFontZH));
			fileheader.setColspan(columns.length);
			fileheader.setBorderWidth(0);
			fileheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(fileheader);
			
			List<MeasureDetail> datalist = mDeatialMapper.queryList(mdetail);
			String[] matchidArray = matchids.split(",");
			boolean exportflag = true;
			String preksmaterialcode = "";
			double grosssum = 0.00;
			double taresum = 0.00;
			double suttlesum = 0.00;
			int carscount = 0;
			for (int k = 0; k < datalist.size(); k++) {
				exportflag = true;
				for (String matchid : matchidArray) {
					if (matchid.equals(datalist.get(k).getMatchid())) {
						exportflag = false;
						break;
					}
				}
				if (exportflag) {
					if(!preksmaterialcode.equals(datalist.get(k).getKs() + datalist.get(k).getMaterialcode())){
						if(!"".equals(preksmaterialcode)){
							PdfPCell footer = new PdfPCell(new Paragraph("", fontZH));
							footer.setBorderWidth(0);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("", fontZH));
							footer.setBorderWidth(0);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("车数：", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(carscount + "", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_LEFT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(grosssum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(taresum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(suttlesum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("打印时间：", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(sdf.format(printDate), fontZH));
							footer.setBorderWidth(0);
							footer.setColspan(2);
							footer.setHorizontalAlignment(Element.ALIGN_LEFT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("打印人：", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(u.getDisplayname(), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_LEFT);
							datatable.addCell(footer);
						}
						
						PdfPHeaderCell header = new PdfPHeaderCell();
						header.addElement(new Paragraph("客商", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(2);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph(datalist.get(k).getKs(), fontZH));
						header.setColspan(4);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("存货名称", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(2);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph(datalist.get(k).getMaterialname(), fontZH));
						header.setColspan(4);
						datatable.addCell(header);

						header = new PdfPHeaderCell();
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.addElement(new Paragraph("序号", headFontZH));
						header.setBackgroundColor(headerColor);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.addElement(new Paragraph("单据号", headFontZH));
						header.setBackgroundColor(headerColor);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("过磅单号", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("车号", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("毛重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("皮重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("净重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("毛重时间", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("皮重时间", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("毛重员", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("皮重员", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("次数", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						preksmaterialcode = datalist.get(k).getKs() + datalist.get(k).getMaterialcode();
						grosssum = 0.00;
						taresum = 0.00;
						suttlesum = 0.00;
						carscount = 0;
					}
					carscount++;
					for (String column : columns) {
						try {
							if ("sn".equals(column)) {
								datatable.addCell(new Paragraph((k + 1) + "", fontZH));
							}else if("printcount".equals(column)){
								int value = 0;
								try{
									value = Integer.parseInt(BeanUtils.getProperty(datalist.get(k), column));
								}catch(Exception e){
									
								}
								datatable.addCell(new Paragraph((value + 1) + "", fontZH));
							} else {
								String value = BeanUtils.getProperty(datalist.get(k), column);
								if("gross".equals(column)){
									grosssum = grosssum + Double.parseDouble(value);
								}else if("tare".equals(column)){
									taresum = taresum + Double.parseDouble(value);
								}else if("suttle".equals(column)){
									suttlesum = suttlesum + Double.parseDouble(value);
								}
								datatable.addCell(new Paragraph(value, fontZH));
							}
						} catch (Exception e) {
							datatable.addCell(new Paragraph("", fontZH));
						}
					}
				}
			}
			if(!"".equals(preksmaterialcode)){
				PdfPCell footer = new PdfPCell(new Paragraph("", fontZH));
				footer.setBorderWidth(0);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("", fontZH));
				footer.setBorderWidth(0);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("车数：", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(carscount + "", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(grosssum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(taresum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(suttlesum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("打印时间：", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(sdf.format(printDate), fontZH));
				footer.setBorderWidth(0);
				footer.setColspan(2);
				footer.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("打印人：", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(u.getDisplayname(), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(footer);
			}
			document.add(datatable);
			document.close();
			try{
				mdetail.setNomatchid(matchids);
				mDeatialMapper.updateMeasurePrintCount(mdetail);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			return new Message(true,fileName);
		} catch (FileNotFoundException f)
		{
			return new Message(false, "找不到临时文件路径");
		}
		catch (Exception e) {
			return new Message(false, "获取打印数据失败！");
		}
	}
	
	/**
	 * 周转打印
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/measuredetail/printMeasureDetailZZ.do")
	public Message printMeasureDetailZZ(MeasureDetail mdetail, String matchids, ModelMap model, HttpServletRequest request) {
		try {
			
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			com.itextpdf.text.Font fontZH = new com.itextpdf.text.Font(bfChinese,8, com.itextpdf.text.Font.NORMAL);
			com.itextpdf.text.Font headFontZH = new com.itextpdf.text.Font(bfChinese,9, com.itextpdf.text.Font.BOLD);
			BaseColor headerColor = new BaseColor(212,212,212);
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			DecimalFormat df = new DecimalFormat("######0.00");
			Date printDate = Calendar.getInstance().getTime();
			
			String fileName = u.getDisplayname() + "_" + System.currentTimeMillis() + ".pdf";
			String filePath = resourceUtils.getResource("pdf.export.path") + fileName;
			
			FileOutputStream out = new FileOutputStream(filePath);
			Document document = new Document(PageSize.A4,5,5,5,5);
			PdfWriter.getInstance(document, out);
			document.open();
			
			String[] columns = new String[] { "sn", "planid", "matchid", "carno", "gross", "tare", "suttle","deduction","deductionsuttle","grosstime", "taretime", "grossoperaname", "tareoperaname","printcount"};
			PdfPTable datatable = new PdfPTable(columns.length);
			float headerwidths[] = new float[] { 0.05f, 0.08f, 0.1f, 0.08f, 0.05f, 0.05f, 0.05f,0.05f,0.05f,0.1f, 0.1f, 0.1f, 0.09f,0.05f};
			
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100);
			datatable.getDefaultCell().setPadding(1);
			datatable.getDefaultCell().setBorderWidth(0.5f);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPHeaderCell fileheader = new PdfPHeaderCell();
			fileheader.addElement(new Paragraph(fileName, headFontZH));
			fileheader.setColspan(columns.length);
			fileheader.setBorderWidth(0);
			fileheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(fileheader);
			
			List<MeasureDetail> datalist = mDeatialMapper.queryList(mdetail);
			String[] matchidArray = matchids.split(",");
			boolean exportflag = true;
			String preksmaterialcode = "";
			double grosssum = 0.00;
			double taresum = 0.00;
			double suttlesum = 0.00;
			double deductionsum = 0.00;
			double dsuttlesum = 0.00;
			int carscount = 0;
			for (int k = 0; k < datalist.size(); k++) {
				exportflag = true;
				for (String matchid : matchidArray) {
					if (matchid.equals(datalist.get(k).getMatchid())) {
						exportflag = false;
						break;
					}
				}
				if (exportflag) {
					if(!preksmaterialcode.equals(datalist.get(k).getKs() + datalist.get(k).getMaterialcode())){
						if(!"".equals(preksmaterialcode)){
							PdfPCell footer = new PdfPCell(new Paragraph("", fontZH));
							footer.setBorderWidth(0);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("", fontZH));
							footer.setBorderWidth(0);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("车数：", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(carscount + "", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_LEFT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(grosssum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(taresum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(suttlesum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(deductionsum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(dsuttlesum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("打印时间：", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(sdf.format(printDate), fontZH));
							footer.setBorderWidth(0);
							footer.setColspan(2);
							footer.setHorizontalAlignment(Element.ALIGN_LEFT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("打印人：", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(u.getDisplayname(), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_LEFT);
							datatable.addCell(footer);
						}
						
						PdfPHeaderCell header = new PdfPHeaderCell();
						header.addElement(new Paragraph("客商", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(2);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph(datalist.get(k).getKs(), fontZH));
						header.setColspan(5);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("存货名称", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(2);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph(datalist.get(k).getMaterialname(), fontZH));
						header.setColspan(5);
						datatable.addCell(header);
						
						header = new PdfPHeaderCell();
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.addElement(new Paragraph("序号", headFontZH));
						header.setBackgroundColor(headerColor);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.addElement(new Paragraph("单据号", headFontZH));
						header.setBackgroundColor(headerColor);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("过磅单号", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("车号", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("毛重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("皮重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("净重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("扣重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("扣净t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("毛重时间", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("皮重时间", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("毛重员", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("皮重员", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("次数", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						preksmaterialcode = datalist.get(k).getKs() + datalist.get(k).getMaterialcode();
						grosssum = 0.00;
						taresum = 0.00;
						suttlesum = 0.00;
						carscount = 0;
					}
					carscount++;
					for (String column : columns) {
						try {
							if ("sn".equals(column)) {
								datatable.addCell(new Paragraph((k + 1) + "", fontZH));
							}else if("printcount".equals(column)){
								int value = 0;
								try{
									value = Integer.parseInt(BeanUtils.getProperty(datalist.get(k), column));
								}catch(Exception e){
									
								}
								datatable.addCell(new Paragraph((value + 1) + "", fontZH));
							} else {
								String value = BeanUtils.getProperty(datalist.get(k), column);
								if("gross".equals(column)){
									grosssum = grosssum + Double.parseDouble(value);
								}else if("tare".equals(column)){
									taresum = taresum + Double.parseDouble(value);
								}else if("suttle".equals(column)){
									suttlesum = suttlesum + Double.parseDouble(value);
								}else if("deduction".equals(column)){
									deductionsum = deductionsum + Double.parseDouble(value);
								}else if("deductionsuttle".equals(column)){
									dsuttlesum = dsuttlesum + Double.parseDouble(value);
								}
								datatable.addCell(new Paragraph(value, fontZH));
							}
						} catch (Exception e) {
							datatable.addCell(new Paragraph("", fontZH));
						}
					}
				}
			}
			if(!"".equals(preksmaterialcode)){
				PdfPCell footer = new PdfPCell(new Paragraph("", fontZH));
				footer.setBorderWidth(0);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("", fontZH));
				footer.setBorderWidth(0);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("车数：", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(carscount + "", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(grosssum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(taresum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(suttlesum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(deductionsum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(dsuttlesum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("打印时间：", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(sdf.format(printDate), fontZH));
				footer.setBorderWidth(0);
				footer.setColspan(2);
				footer.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("打印人：", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(u.getDisplayname(), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(footer);
			}
			document.add(datatable);
			document.close();
			try{
				mdetail.setNomatchid(matchids);
				mDeatialMapper.updateMeasurePrintCount(mdetail);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			return new Message(true,fileName);
		} catch (FileNotFoundException f)
		{
			return new Message(false, "找不到临时文件路径");
		}
		catch (Exception e) {
			return new Message(false, "获取打印数据失败！");
		}
	}
	
	/**
	 * 规格打印
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/measuredetail/printMeasureDetailSpec.do")
	public Message printspec(MeasureDetail mdetail, String matchids, ModelMap model, HttpServletRequest request) {
		try {
			
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			com.itextpdf.text.Font fontZH = new com.itextpdf.text.Font(bfChinese,8, com.itextpdf.text.Font.NORMAL);
			com.itextpdf.text.Font headFontZH = new com.itextpdf.text.Font(bfChinese,9, com.itextpdf.text.Font.BOLD);
			BaseColor headerColor = new BaseColor(212,212,212);
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			DecimalFormat df = new DecimalFormat("######0.00");
			Date printDate = Calendar.getInstance().getTime();
			
			String fileName = u.getDisplayname() + "_" + System.currentTimeMillis() + ".pdf";
			String filePath = resourceUtils.getResource("pdf.export.path") + fileName;
			
			FileOutputStream out = new FileOutputStream(filePath);
			Document document = new Document(PageSize.A4,5,5,5,5);
			PdfWriter.getInstance(document, out);
			document.open();
			
			String[] columns = new String[] { "sn", "planid", "matchid","materialcode","materialname","materialspec","carno", "gross", "tare", "suttle","grosstime", "taretime", "grossoperaname", "tareoperaname","printcount"};
			PdfPTable datatable = new PdfPTable(columns.length);
			float headerwidths[] = new float[] { 0.05f, 0.12f, 0.1f, 0.08f, 0.08f, 0.08f, 0.06f, 0.05f, 0.05f, 0.05f, 0.12f, 0.12f, 0.06f, 0.06f,0.05f };
			
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100);
			datatable.getDefaultCell().setPadding(1);
			datatable.getDefaultCell().setBorderWidth(0.5f);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPHeaderCell fileheader = new PdfPHeaderCell();
			fileheader.addElement(new Paragraph(fileName, headFontZH));
			fileheader.setColspan(columns.length);
			fileheader.setBorderWidth(0);
			fileheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(fileheader);
			
			List<MeasureDetail> datalist = mDeatialMapper.queryList(mdetail);
			String[] matchidArray = matchids.split(",");
			boolean exportflag = true;
			String preks = "";
			double grosssum = 0.00;
			double taresum = 0.00;
			double suttlesum = 0.00;
			int carscount = 0;
			for (int k = 0; k < datalist.size(); k++) {
				exportflag = true;
				for (String matchid : matchidArray) {
					if (matchid.equals(datalist.get(k).getMatchid())) {
						exportflag = false;
						break;
					}
				}
				if (exportflag) {
					if(!preks.equals(datalist.get(k).getKs())){
						if(!"".equals(preks)){
							PdfPCell footer = new PdfPCell(new Paragraph("", fontZH));
							footer.setBorderWidth(0);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("", fontZH));
							footer.setBorderWidth(0);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("车数：", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(carscount + "", fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_LEFT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(grosssum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(taresum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(df.format(suttlesum), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_CENTER);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("打印时间：", fontZH));
							footer.setBorderWidth(0);
							footer.setColspan(2);
							footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(sdf.format(printDate), fontZH));
							footer.setBorderWidth(0);
							footer.setColspan(3);
							footer.setHorizontalAlignment(Element.ALIGN_LEFT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph("打印人：", fontZH));
							footer.setBorderWidth(0);
							footer.setColspan(2);
							footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
							datatable.addCell(footer);
							footer = new PdfPCell(new Paragraph(u.getDisplayname(), fontZH));
							footer.setBorderWidth(0);
							footer.setHorizontalAlignment(Element.ALIGN_LEFT);
							datatable.addCell(footer);
						}
						
						PdfPHeaderCell header = new PdfPHeaderCell();
						header.addElement(new Paragraph("客商", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(2);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph(datalist.get(k).getKs(), fontZH));
						header.setColspan(13);
						datatable.addCell(header);
						
						header = new PdfPHeaderCell();
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.addElement(new Paragraph("序号", headFontZH));
						header.setBackgroundColor(headerColor);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						header.addElement(new Paragraph("单据号", headFontZH));
						header.setBackgroundColor(headerColor);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("过磅单号", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("存货编号", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("存货名称", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("规格型号", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("车号", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("毛重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("皮重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("净重t", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("毛重时间", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("皮重时间", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("毛重员", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("皮重员", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("次数", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						preks = datalist.get(k).getKs();
						grosssum = 0.00;
						taresum = 0.00;
						suttlesum = 0.00;
						carscount = 0;
					}
					carscount++;
					for (String column : columns) {
						try {
							if ("sn".equals(column)) {
								datatable.addCell(new Paragraph((k + 1) + "", fontZH));
							}else if("printcount".equals(column)){
								int value = 0;
								try{
									value = Integer.parseInt(BeanUtils.getProperty(datalist.get(k), column));
								}catch(Exception e){
									
								}
								datatable.addCell(new Paragraph((value + 1) + "", fontZH));
							} else {
								String value = BeanUtils.getProperty(datalist.get(k), column);
								if("gross".equals(column)){
									grosssum = grosssum + Double.parseDouble(value);
								}else if("tare".equals(column)){
									taresum = taresum + Double.parseDouble(value);
								}else if("suttle".equals(column)){
									suttlesum = suttlesum + Double.parseDouble(value);
								}
								datatable.addCell(new Paragraph(value, fontZH));
							}
						} catch (Exception e) {
							datatable.addCell(new Paragraph("", fontZH));
						}
					}
				}
			}
			if(!"".equals(preks)){
				PdfPCell footer = new PdfPCell(new Paragraph("", fontZH));
				footer.setBorderWidth(0);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("", fontZH));
				footer.setBorderWidth(0);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("车数：", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(carscount + "", fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(grosssum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(taresum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(df.format(suttlesum), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("打印时间：", fontZH));
				footer.setBorderWidth(0);
				footer.setColspan(2);
				footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(sdf.format(printDate), fontZH));
				footer.setBorderWidth(0);
				footer.setColspan(3);
				footer.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("打印人：", fontZH));
				footer.setBorderWidth(0);
				footer.setColspan(2);
				footer.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph(u.getDisplayname(), fontZH));
				footer.setBorderWidth(0);
				footer.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(footer);
			}
			document.add(datatable);
			document.close();
			try{
				mdetail.setNomatchid(matchids);
				mDeatialMapper.updateMeasurePrintCount(mdetail);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			return new Message(true,fileName);
		} catch (FileNotFoundException f)
		{
			return new Message(false, "找不到临时文件路径");
		}
		catch (Exception e) {
			return new Message(false, "获取打印数据失败！");
		}
	}

	@RequestMapping(value = "/pdf/download.do")
	public void downloadpdf(HttpServletResponse response,String fn,ModelMap model) {
		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition","attachment;filename=" + new String(fn.getBytes("gb2312"),"iso-8859-1"));

			BufferedInputStream input = new BufferedInputStream(new FileInputStream(resourceUtils.getResource("pdf.export.path") + fn));
			byte buffBytes[] = new byte[1024];
			OutputStream os = response.getOutputStream();
			int read = 0;
			while ((read = input.read(buffBytes)) != -1) {
				os.write(buffBytes, 0, read);
			}
			os.flush();
			os.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印日志
	 * 
	 * @param q
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measuredetail/printlog.do")
	public Message printlog(MeasureDetail mdetail, ReportLog reportLog) {
		Message msg = new Message();
		try {
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			reportLog.setOperaman(u.getDisplayname());// 获取登录人
			measureDetailService.printLog(mdetail, reportLog);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}
}
