package com.talent.measure.dao;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.measure.model.MeasuerTaskReport;
import com.talent.measure.model.MeasurePhoto;
import com.talent.measure.model.MeasurePrintBill;
import com.talent.measure.model.MeasureTaskDoResult;

public interface  MeasureTaskReportDao {

	/**
	 * 获取衡器时间段内的过毛车数与过皮车数以及总重量等信息
	 */
	public List<MeasuerTaskReport> getCarWeightTaskCount(MeasuerTaskReport mtr) throws DataAccessException;
	//获取计量员某段时间内的过毛过皮车数以及总重量信息
	public List<MeasuerTaskReport> getCarUserCompleteCount(MeasuerTaskReport mtr) throws DataAccessException;
	
	//保存任务处理结果信息
	public int saveTaskDoResult(MeasureTaskDoResult mtr) throws DataAccessException;
	
	//查询任务处理结果信息
	public List<MeasureTaskDoResult> getTaskDoResult(MeasureTaskDoResult mtr) throws DataAccessException;
	
	//查询一段时间所有计量员的过磅车数以及平均耗时
	public List<MeasureTaskDoResult> getAllTaskDoResultAvgTime(MeasureTaskDoResult mtr) throws DataAccessException;
	
	//保存物流号与图片列表
	public int saveMeasurePhoto(MeasurePhoto mtr) throws DataAccessException;
	
	//获取过磅时的图片列表
	public List<MeasurePhoto> getMeasurePhoto(MeasurePhoto mtr) throws DataAccessException;
	
	//保存票据打印信息
	public int savePrintBill(MeasurePrintBill mtr) throws DataAccessException;
	
	public int getPrintCount(MeasurePrintBill mtr) throws DataAccessException;
		
	//判断该matchid下面该照片名字存在条数
	public int checkPhotoIsIn(MeasurePhoto mtr) throws DataAccessException; 
}
