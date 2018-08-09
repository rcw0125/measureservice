package com.talent.measure.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.base.model.Message;
import com.talent.measure.dao.MeasureTaskReportDao;
import com.talent.measure.model.MeasuerTaskReport;
import com.talent.measure.model.MeasurePhoto;
import com.talent.measure.model.MeasurePrintBill;
import com.talent.measure.model.MeasureTaskDoResult;

@Controller
public class MeasureTaskReportController {

	@Autowired
	private MeasureTaskReportDao measureTaskReportDao;

	// 获取某段时间 衡器的过磅车数与过磅重量
	@ResponseBody
	@RequestMapping(value = "/MeasureTaskReport/getCarWeightTaskCount.do")
	public List<MeasuerTaskReport> getCarWeightTaskCount(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		MeasuerTaskReport mtr = GetMeasuerTaskReport(jsonParams);
		List<MeasuerTaskReport> list = measureTaskReportDao.getCarWeightTaskCount(mtr);
		return list;
	}

	// 获取某段时间 计量员的过磅车数与过磅重量
	@ResponseBody
	@RequestMapping(value = "/MeasureTaskReport/getCarUserCompleteCount.do")
	public List<MeasuerTaskReport> getCarUserCompleteCount(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		MeasuerTaskReport mtr = GetMeasuerTaskReport(jsonParams);
		List<MeasuerTaskReport> list = measureTaskReportDao.getCarUserCompleteCount(mtr);
		return list;
	}

	// 记录坐席计量员处理任务的开始 结束时间以及对应的时长等信息
	@ResponseBody
	@RequestMapping(value = "/MeasureTaskReport/saveTaskDoResult.do")
	public Message saveTaskDoResult(String jsonParams) {
		Message msg = new Message();
		int successcount = 0;
		if (jsonParams.length() == 0) {
			msg = MeasureSeatController.setMessage(successcount, "请求串为空");
			return msg;
		}
		MeasureTaskDoResult mtd = null;
		try {
			mtd = getObjectsFromJson(jsonParams, MeasureTaskDoResult.class).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double mi = GetMi(String.valueOf(mtd.getTaskbegintime()), String.valueOf(mtd.getTaskendtime()));
		mtd.setTimecount(mi);
		successcount = measureTaskReportDao.saveTaskDoResult(mtd);
		// msg="successcount:"+successcount;
		msg = MeasureSeatController.setMessage(successcount, "请求完成");
		return msg;
	}

	// 获取 计量员处理任务的记录
	@ResponseBody
	@RequestMapping(value = "/MeasureTaskReport/getTaskDoResult.do")
	public List<MeasureTaskDoResult> getTaskDoResult(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		MeasureTaskDoResult mtd = null;
		try {
			mtd = getObjectsFromJson(jsonParams, MeasureTaskDoResult.class).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (String.valueOf(mtd.getTaskbegintime()).length() == 0) {
			return null;
		}
		if (String.valueOf(mtd.getTaskendtime()).length() == 0) {
			return null;
		}
		List<MeasureTaskDoResult> list = measureTaskReportDao.getTaskDoResult(mtd);
		return list;
	}

	// 获取 一段时间所有计量员的过磅车数以及平均耗时
	@ResponseBody
	@RequestMapping(value = "/MeasureTaskReport/getAllTaskDoResultAvgTime.do")
	public List<MeasureTaskDoResult> getAllTaskDoResultAvgTime(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		MeasureTaskDoResult mtd = null;
		try {
			mtd = getObjectsFromJson(jsonParams, MeasureTaskDoResult.class).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (String.valueOf(mtd.getTaskbegintime()).length() == 0) {
			return null;
		}
		if (String.valueOf(mtd.getTaskendtime()).length() == 0) {
			return null;
		}
		List<MeasureTaskDoResult> list = measureTaskReportDao.getAllTaskDoResultAvgTime(mtd);
		return list;
	}

	// 记录物流号与照片路径的保存关系
	@ResponseBody
	@RequestMapping(value = "/MeasureTaskReport/saveMeasurePhoto.do")
	public Message saveMeasurePhoto(String jsonParams) {
		Message msg = new Message();
		int successcount = 0;
		if (jsonParams.length() == 0) {
			msg = MeasureSeatController.setMessage(successcount, "请求串为空");
			return msg;
		}
		List<MeasurePhoto> mtd = null;

		try {
			mtd = getObjectsFromJson(jsonParams, MeasurePhoto.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < mtd.size(); i++) {
			try {
				MeasurePhoto mPh = mtd.get(i);
				successcount = 0;
				successcount = measureTaskReportDao.checkPhotoIsIn(mPh);
				if (successcount == 0) {
					successcount = measureTaskReportDao.saveMeasurePhoto(mPh);
				}

			} catch (Exception e) {

			}
		}
		msg = MeasureSeatController.setMessage(successcount, "请求处理结束");
		// msg="{"+"\"successcount\""+":"+successcount+"}";
		return msg;
	}

	// 获取 过磅时保存的图片
	@ResponseBody
	@RequestMapping(value = "/MeasureTaskReport/getMeasurePhoto.do")
	public List<MeasurePhoto> getMeasurePhoto(String jsonParams) {
		if (jsonParams.length() == 0) {
			return null;
		}
		MeasurePhoto mtd = null;
		try {
			mtd = getObjectsFromJson(jsonParams, MeasurePhoto.class).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mtd.getMatchid().length() == 0) {
			return null;
		}

		List<MeasurePhoto> list = measureTaskReportDao.getMeasurePhoto(mtd);
		return list;
	}

	// 保存打印的日志
	@ResponseBody
	@RequestMapping(value = "/MeasureTaskReport/savePrintBill.do")
	public Message savePrintBill(String jsonParams) {
		Message msg = new Message();
		int successcount = 0;
		if (jsonParams.length() == 0) {
			msg = MeasureSeatController.setMessage(successcount, "请求串为空");
			return msg;
		}
		MeasurePrintBill mtd = null;
		try {
			mtd = getObjectsFromJson(jsonParams, MeasurePrintBill.class).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		successcount = measureTaskReportDao.savePrintBill(mtd);
		msg = MeasureSeatController.setMessage(successcount, "请求处理结束");
		// msg="{"+"\"successcount\""+":"+successcount+"}";
		return msg;
	}

	private double GetMi(String bTime, String eTime) {
		double rtInfo = 0;
		Date bDate = stringToDate(bTime, "yyyyMMddHHmmss");
		Date eDate = stringToDate(eTime, "yyyyMMddHHmmss");
		long l = eDate.getTime() - bDate.getTime();
		rtInfo = (double) l * 0.001;
		return rtInfo;
	}

	// String 转 date
	public Date stringToDate(String inString, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date currentTime_2 = null;
		try {
			currentTime_2 = formatter.parse(inString);
		} catch (ParseException e) {

		}
		return currentTime_2;
	}

	// 将jso串转为 modle
	public MeasuerTaskReport GetMeasuerTaskReport(String jsonParams) {
		ObjectMapper om = new ObjectMapper();
		MeasuerTaskReport mtr = new MeasuerTaskReport();
		try {
			mtr = om.readValue(jsonParams, MeasuerTaskReport.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mtr;
	}

	private <T> List<T> getObjectsFromJson(String in, Class<T> clsT) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("deprecation")
		JsonParser parser = objectMapper.getJsonFactory().createJsonParser(in);

		JsonNode nodes = parser.readValueAsTree();
		List<T> list = new ArrayList<T>(nodes.size());
		for (JsonNode node : nodes) {
			list.add(objectMapper.readValue(node.toString(), clsT));
		}
		return list;
	}
}
