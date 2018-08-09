package com.talent.measure.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.QualityInterfaceDao;
import com.talent.measure.model.QualityInterface;

@Service
@Component
@SuppressWarnings({ "deprecation" })
public class QualityInterfaceDaoImpl extends BaseDaoiBatis implements QualityInterfaceDao {

	@Override
	public void insertNoticeQualityData(QualityInterface qualityInterface) {
		getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertNoticebillHead", qualityInterface);
		getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertNoticebillBody", qualityInterface);
		getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertDhdHead", qualityInterface);
		getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertDhdBody", qualityInterface);
	}

	@Override
	public void updateMeasureData(QualityInterface qualityInterface) {
		getMSSQLSqlMapClientTemplate().update("QualityInterface.updateSuttleData", qualityInterface);
	}

	@Override
	public void insertInGate(QualityInterface qualityInterface) {

		try {
			int i = (Integer)  getMSSQLSqlMapClientTemplate().queryForObject("QualityInterface.queryCountByMatchid", qualityInterface);
			if(i > 0){
				getMSSQLSqlMapClientTemplate().insert("QualityInterface.updateInoutGate", qualityInterface);
			} else if (i == 0){
				getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertInoutGate", qualityInterface);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			int i = (Integer)  getMSSQLSqlMapClientTemplate().queryForObject("QualityInterface.queryCountByNoticebid", qualityInterface);
			if(i > 0){
				getMSSQLSqlMapClientTemplate().insert("QualityInterface.updateNoticebillHead", qualityInterface);
			} else if (i == 0){
				getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertNoticebillHead", qualityInterface);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			int flag = (Integer) getMSSQLSqlMapClientTemplate().queryForObject("QualityInterface.queryByNoticebid",qualityInterface);
			if(flag==0){
				getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertNoticebillBody", qualityInterface);
			}else{
				qualityInterface.setBillbodyid(qualityInterface.getBillbodyid() + "2");
				getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertNoticebillBody", qualityInterface);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			int flag = (Integer) getMSSQLSqlMapClientTemplate().queryForObject("QualityInterface.queryBydjno",qualityInterface);
			if (flag == 0) {
				try{
					getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertDhdHead", qualityInterface);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			int zflag = (Integer) getMSSQLSqlMapClientTemplate().queryForObject("QualityInterface.queryBybid",qualityInterface);
			if (zflag == 0) {
				try{
					getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertDhdBody", qualityInterface);
				}catch(Exception e){
					
				}
			} else {
				qualityInterface.setCarriveorder_bid(qualityInterface.getCarriveorder_bid() + "2");
				try{
					getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertDhdBody", qualityInterface);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertOutGate(QualityInterface qualityInterface) {
		int i = (Integer)  getMSSQLSqlMapClientTemplate().queryForObject("QualityInterface.queryCountByMatchid", qualityInterface);
		if(i > 0){
			getMSSQLSqlMapClientTemplate().insert("QualityInterface.updateInoutGate", qualityInterface);
		} else if (i == 0){
			getMSSQLSqlMapClientTemplate().insert("QualityInterface.insertInoutGate", qualityInterface);
		}
	}

	@Override
	public void returnSampleFlag(QualityInterface qualityInterface) {
		getOracleSqlMapClientTemplate().update("QualityInterface.returnSampleFlag", qualityInterface);
		getOracleSqlMapClientTemplate().update("QualityInterface.insertQualityInfo", qualityInterface);
		getOracleSqlMapClientTemplate().update("QualityInterface.returnMeasureDeduction", qualityInterface);
	}
	
	public void cancelSampleFlag(QualityInterface qualityInterface) {
		getOracleSqlMapClientTemplate().update("QualityInterface.cancelSampleFlag", qualityInterface);
		getOracleSqlMapClientTemplate().update("QualityInterface.cancelQualityInfo", qualityInterface);
	}
	
	public QualityInterface queryMeasureInfoReturnQA(String matchid) throws DataAccessException {
		return (QualityInterface) getOracleSqlMapClientTemplate()
				.queryForObject("QualityInterface.queryMeasureInfoReturnQA", matchid);
	}
	/**
	 * 撤销取样信息
	 * @param matchid
	 */ 
	public void updateSatus(QualityInterface qualityInterface) throws DataAccessException{
		String noticebillbodyid =(String)getOracleSqlMapClientTemplate().queryForObject("QualityInterface.queryNoticebillid", qualityInterface.getMatchid());
		qualityInterface.setNoticebillbodyid(noticebillbodyid);
		getMSSQLSqlMapClientTemplate().update("QualityInterface.updateSatus", qualityInterface);
		getMSSQLSqlMapClientTemplate().update("QualityInterface.updatenoticebill", qualityInterface);
		getMSSQLSqlMapClientTemplate().update("QualityInterface.updateDhdbill", qualityInterface);
	}
	/**
	 * 根据车号和计量时间获取线材净重
	 * @param qualityInterface
	 * @return
	 */
	public String queryWireValue(QualityInterface qualityInterface) throws DataAccessException{
		return (String)getMSSQLSqlMapClientTemplate2().queryForObject("WirebarCompareMap.queryWireValue", qualityInterface);
	}
}