package com.talent.report.controller;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * ClassName:PdfReportM1HeaderFooter2 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2013-9-13 上午08:59:00 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.5
 * @see
 */
public class PdfReportM1HeaderFooter extends PdfPageEventHelper {

	String header;
	PdfTemplate total;
	BaseFont bfChinese = null;
	Font fontDetail = null;
	Font fontDetail2 = null;

	public void setHeader(String header) {
		this.header = header;
	}

	public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(30, 16);
	}

	public void onEndPage(PdfWriter writer, Document document) {

		Rectangle pageSize = writer.getBoxSize("art");

		try {
			if (bfChinese == null) {
				bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			}
			if (fontDetail == null) {
				fontDetail = new Font(bfChinese, 12, Font.NORMAL);// 数据体字体
				fontDetail2 = new Font(bfChinese, 15, Font.NORMAL);// 数据体字体
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = "第 " + writer.getPageNumber() + "页/共 ";


		// 页眉
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,new Phrase("邢钢轨道衡计量单", fontDetail2), document.left(), document.top() + 10, 0);
		PdfPTable table = new PdfPTable(2);

		try {
			table.setWidths(new int[] { 1, 1 });
			table.setWidthPercentage(100F);// 占据百分百宽度
			table.setTotalWidth(pageSize.getRight() - pageSize.getLeft() - 100);

			PdfPCell cell1 = new PdfPCell(new Phrase(text, fontDetail));
			PdfPCell cell2 = new PdfPCell(Image.getInstance(total));

			cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell1.setBorder(Rectangle.NO_BORDER);
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setBorder(Rectangle.NO_BORDER);

			table.addCell(cell1);
			table.addCell(cell2);
			table.writeSelectedRows(0, -1, document.left(), document.bottom(), writer.getDirectContent());
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}

	}

	public void onCloseDocument(PdfWriter writer, Document document) {

		try {
			if (bfChinese == null) {
				bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
			}
			if (fontDetail == null) {
				fontDetail = new Font(bfChinese, 12, Font.NORMAL);// 数据体字体
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
				new Phrase(String.format("%d页", writer.getPageNumber()), fontDetail), 2, 2, 0);
	}
}