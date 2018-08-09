package com.talent.measure.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.MeasureTaskReportDao;
import com.talent.measure.model.MeasuerTaskReport;
import com.talent.measure.model.MeasurePhoto;
import com.talent.measure.model.MeasurePrintBill;
import com.talent.measure.model.MeasureTaskDoResult;

@Service
@Component
@SuppressWarnings({ "deprecation", "unchecked" })
public class MeasureTaskReportDaoImpl extends BaseDaoiBatis implements MeasureTaskReportDao {
	
	/**
	 * 获取衡器过磅总数
	 */
	@Override
	public List<MeasuerTaskReport> getCarWeightTaskCount(MeasuerTaskReport mtr) throws DataAccessException{ 		
			List<MeasuerTaskReport> list  = new ArrayList<MeasuerTaskReport>();	
			try{
				list = getOracleSqlMapClientTemplate().queryForList("measureTaskReport.getCarWeightTaskCount", mtr);
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}
	/**
	 * 获取计量员过磅数
	 */
	@Override
	public List<MeasuerTaskReport> getCarUserCompleteCount(MeasuerTaskReport mtr) throws DataAccessException{ 		
		List<MeasuerTaskReport> list  = new ArrayList<MeasuerTaskReport>();	
		try{
			list = getOracleSqlMapClientTemplate().queryForList("measureTaskReport.getCarUserCompleteCount", mtr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		}
	//保存任务处理结果数据
	@Override
	public int saveTaskDoResult(MeasureTaskDoResult mtr) throws DataAccessException{
		int rti=0;
		try {
			 getOracleSqlMapClientTemplate().insert("measureTaskReport.saveTaskDoResult",mtr);
			 rti=1;
		} catch (Exception e) {
			rti=0;
		}
		return rti;
	}
	
	/**
	 * 获取任务处理结果数据
	 */
	@Override
	public List<MeasureTaskDoResult> getTaskDoResult(MeasureTaskDoResult mtr) throws DataAccessException{ 		
		List<MeasureTaskDoResult> list  = new ArrayList<MeasureTaskDoResult>();	
		try{
			list = getOracleSqlMapClientTemplate().queryForList("measureTaskReport.getTaskDoResult", mtr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取一段时间所有计量员的过磅车数以及平均耗时
	 */
	@Override
	public List<MeasureTaskDoResult> getAllTaskDoResultAvgTime(MeasureTaskDoResult mtr) throws DataAccessException{ 		
		List<MeasureTaskDoResult> list  = new ArrayList<MeasureTaskDoResult>();	
		try{
			list = getOracleSqlMapClientTemplate().queryForList("measureTaskReport.getAllTaskDoResultAvgTime", mtr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int saveMeasurePhoto(MeasurePhoto mtr) throws DataAccessException
	{
		int rti=0;
		try {
			 getOracleSqlMapClientTemplate().insert("measureTaskReport.saveMeasurePhoto",mtr);
			 rti=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rti=0;
		}
		return rti;
	}
	
	//获取过磅时的图片列表
	@Override
	public List<MeasurePhoto> getMeasurePhoto(MeasurePhoto mtr) throws DataAccessException{
		List<MeasurePhoto> list  = new ArrayList<MeasurePhoto>();	
		try{
			list = getOracleSqlMapClientTemplate().queryForList("measureTaskReport.getMeasurePhoto", mtr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;	 
	}
	@Override
	public int savePrintBill(MeasurePrintBill mtr) throws DataAccessException{
		int rti=0;
		try {
			 getOracleSqlMapClientTemplate().insert("measureTaskReport.savePrintBill",mtr);
			 rti=1;
		} catch (Exception e) {
			e.printStackTrace();
			rti=0;
		}
		return rti;
	}
	
	public int getPrintCount(MeasurePrintBill mtr) throws DataAccessException{
		try{
			return (int)getOracleSqlMapClientTemplate().queryForObject("measureTaskReport.getprintcount",mtr);
		}catch(Exception e){
			return 1;
		}
	}
	
	//判断该matchid下面该照片名字存在条数
	@Override
	public int checkPhotoIsIn(MeasurePhoto mtr) throws DataAccessException
	{
		int rti=0;
		try {
			rti=(int) getOracleSqlMapClientTemplate().queryForObject("measureTaskReport.checkPhotoIsIn",mtr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			rti=0;
		}
		return rti;
	}
}
