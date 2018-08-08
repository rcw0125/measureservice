package com.talent.report.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.talent.core.controller.BaseController;
import com.talent.core.model.Message;
import com.talent.core.model.Pagination;
import com.talent.core.privilege.model.User;
import com.talent.core.util.ResourceUtils;
import com.talent.report.model.MeasureReport;
import com.talent.report.model.Printlog;
import com.talent.report.model.TareLog;
import com.talent.report.service.mapper.MeasureReportMapper;

@Controller
public class MeasureReportController extends BaseController {
	@Resource
	private MeasureReportMapper measurereportMapper;
	
	@Autowired
	private ResourceUtils resourceUtils;

	/**
	 * 查询计量明细入口
	 * 
	 */
	@RequestMapping(value = "/measurereportdetail/visit.do")
	public String visitDetail(ModelMap model) {
		return "measurereportdetail";
	}

	/**
	 * 查询火运计量明细入口
	 * 
	 */
	@RequestMapping(value = "/tmeasurereport/visit.do")
	public String visittDetail(ModelMap model) {
		return "tmeasurereport";
	}

	/**
	 * 查询汇总明细入口
	 * 
	 */
	@RequestMapping(value = "/measuresum/visit.do")
	public String visitSum(ModelMap model) {
		return "measuresum";
	}
	
	/**
	 * 查询计量日志入口
	 * 
	 */
	@RequestMapping(value = "/measurereportlog/visit.do")
	public String visitDetailLog(ModelMap model) {
		return "measurereportlog";
	}
	
	/**
	 * 查询皮重日志入口
	 * 
	 */
	@RequestMapping(value = "/tareinfo/visit.do")
	public String visittareinfo(ModelMap model) {
		return "tareinfo";
	}


	@ResponseBody
	@RequestMapping(value = "/measureReport/queryReportDetail.do")
	public Message queryPage(MeasureReport mreport, ModelMap model, Pagination<MeasureReport> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(measurereportMapper.queryReportDetail(mreport));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/measureReport/queryMeasureSum.do")
	public Message queryReportSum(MeasureReport mreport, ModelMap model, Pagination<MeasureReport> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(measurereportMapper.queryReportSum(mreport));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/measureReport/queryReportLog.do")
	public Message queryReportLog(MeasureReport mreport, ModelMap model, Pagination<MeasureReport> page) {
		Message msg = new Message();
		try {
			msg = buildGridData(measurereportMapper.queryReportLog(mreport));
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/measureReport/queryCount.do")
	public Message queryCount(MeasureReport mreport, ModelMap model, Pagination<MeasureReport> page) {
		Message msg = new Message();
		try {
			MeasureReport mr = measurereportMapper.queryCount(mreport);
			msg.setData(mr);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/measureReport/queryLogCount.do")
	public Message queryLogCount(MeasureReport mreport, ModelMap model, Pagination<MeasureReport> page) {
		Message msg = new Message();
		try {
			MeasureReport mr = measurereportMapper.queryLogCount(mreport);
			msg.setData(mr);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/measureReport/querySumCount.do")
	public Message querySumCount(MeasureReport mreport, ModelMap model, Pagination<MeasureReport> page) {
		Message msg = new Message();
		try {
			MeasureReport mr = measurereportMapper.querySumCount(mreport);
			msg.setData(mr);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/measureReport/queryTareinfo.do")
	public Message queryReportDetail(TareLog tlog, ModelMap model, Pagination<TareLog> page) {
		Message msg = new Message();
		try {
			if ("1".equals(tlog.getTypes())) {
				msg = buildGridData(measurereportMapper.queryTareinfo(tlog));
			} else {
				msg = buildGridData(measurereportMapper.queryTareloginfo(tlog));
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}

	/**
	 * 
	 * @param matchid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/measureReport/queryPrintinfo.do")
	public Message queryPrintinfo(Printlog printlog) {
		Message msg = new Message();
		try {
			msg = buildGridData(measurereportMapper.queryPrintinfo(printlog));

		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作失败！");
		}

		return msg;
	}

	public PdfTemplate tpl;

	@ResponseBody
	@RequestMapping(value = "/measurereport/printtMeasureDetailZZ.do")
	public Message printMeasureDetailZZ(MeasureReport mreport, String matchids, ModelMap model,
			HttpServletRequest request) {
		try {
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			com.itextpdf.text.Font fontZH = new com.itextpdf.text.Font(bfChinese, 10, com.itextpdf.text.Font.NORMAL);
			com.itextpdf.text.Font headFontZH = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.BOLD);
			BaseColor headerColor = new BaseColor(255, 255, 255);
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			DecimalFormat df = new DecimalFormat("######0.00");
			SimpleDateFormat sdf1 = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
			sdf1.applyPattern("yyyyMMddHHmmss");

			String fileName = u.getDisplayname() + "_" + sdf1.format(System.currentTimeMillis()) + ".pdf";
			String fileData = "打印时间：" + sdf.format(System.currentTimeMillis());
			String filePath = resourceUtils.getResource("pdf.export.path") + fileName;

			FileOutputStream out = new FileOutputStream(filePath);
			Document document = new Document(PageSize.A4, 25, 25, 25, 25);
			PdfWriter writer = PdfWriter.getInstance(document, out);
			
			PdfReportM1HeaderFooter headerFooter = new PdfReportM1HeaderFooter();
			writer.setBoxSize("art", PageSize.A4);
			writer.setPageEvent(headerFooter);
			
			document.open();
			
			String[] columns = new String[] { "sn", "planid", "matchid", "carno", "gross", "tare", "suttle","deduction", "deductionsuttle", "suttletimeHHMMSS" };
			PdfPTable datatable = new PdfPTable(columns.length);
			float headerwidths[] = new float[] { 0.07f, 0.14f, 0.14f, 0.13f, 0.08f, 0.08f, 0.08f, 0.08f, 0.08f, 0.11f };
			
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100);
			datatable.getDefaultCell().setPadding(1);
			datatable.getDefaultCell().setBorderWidth(0.5f);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

			PdfPHeaderCell fileheader = new PdfPHeaderCell();
			fileheader.addElement(new Paragraph(fileName + "　　　" + fileData + "　　　计量单位：吨", headFontZH));
			fileheader.setColspan(columns.length);
			fileheader.setBorderWidth(0);
			fileheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(fileheader);

			List<MeasureReport> datalist = measurereportMapper.queryReportDetail(mreport);
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
					if (!preksmaterialcode.equals(datalist.get(k).getGroupno())) {
						if (!"".equals(preksmaterialcode)) {
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
							footer = new PdfPCell(new Paragraph("", fontZH));
							footer.setBorderWidth(0);
							datatable.addCell(footer);

						}

						PdfPHeaderCell header = new PdfPHeaderCell();
						header.addElement(new Paragraph("计量单号：7777777", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(2);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("火车编号：" + datalist.get(k).getGroupno(), headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(3);
						header.setHorizontalAlignment(Element.ALIGN_RIGHT);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(
								new Paragraph("计量日期：" + datalist.get(k).getSuttletime().substring(0, 10), headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(3);
						header.setHorizontalAlignment(Element.ALIGN_RIGHT);
						datatable.addCell(header);
						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("计量员：" + datalist.get(k).getSuttleoperaname(), headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(2);
						header.setHorizontalAlignment(Element.ALIGN_RIGHT);
						datatable.addCell(header);

						header = new PdfPHeaderCell();
						header.addElement(new Paragraph("客商", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setColspan(1);
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
						header.setColspan(3);
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
						header.addElement(new Paragraph("时间", headFontZH));
						header.setBackgroundColor(headerColor);
						header.setHorizontalAlignment(Element.ALIGN_CENTER);
						datatable.addCell(header);
						preksmaterialcode = datalist.get(k).getGroupno();
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
							} else if ("printcount".equals(column)) {
								int value = 0;
								try {
									value = Integer.parseInt(BeanUtils.getProperty(datalist.get(k), column));
								} catch (Exception e) {

								}
								datatable.addCell(new Paragraph((value + 1) + "", fontZH));
							} else {
								String value = BeanUtils.getProperty(datalist.get(k), column);
								if ("gross".equals(column)) {
									grosssum = grosssum + Double.parseDouble(value);
								} else if ("tare".equals(column)) {
									taresum = taresum + Double.parseDouble(value);
								} else if ("suttle".equals(column)) {
									suttlesum = suttlesum + Double.parseDouble(value);
								} else if ("deduction".equals(column)) {
									deductionsum = deductionsum + Double.parseDouble(value);
								} else if ("deductionsuttle".equals(column)) {
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
			if (!"".equals(preksmaterialcode)) {
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
				footer = new PdfPCell(new Paragraph("", fontZH));
				footer.setBorderWidth(0);
				datatable.addCell(footer);
				footer = new PdfPCell(new Paragraph("", fontZH));
				footer.setBorderWidth(0);
				datatable.addCell(footer);
			}
			document.add(datatable);			
			document.close();
			
			try {
				mreport.setNomatchid(matchids);
				measurereportMapper.updateMeasurePrintCount(mreport);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return new Message(true, fileName);
		} catch (FileNotFoundException f) {
			f.printStackTrace();
			return new Message(false, "找不到临时文件路径");
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(false, "获取打印数据失败！");
		}
	}

}
