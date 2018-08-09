package com.talent.measure.dao.impl;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.base.model.PageModel;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.MeasureDao;
import com.talent.measure.model.ConfigModel;
import com.talent.measure.model.Measure;
import com.talent.measure.model.MeasureTareHistory;
import com.talent.measure.model.PrintModel;
import com.talent.measure.model.TaskCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Service
@Component
@SuppressWarnings({ "deprecation","unchecked" })
public class MeasureDaoImpl extends BaseDaoiBatis implements MeasureDao {

	/**
	 * 获取供应商、客户、库房、厂内单位
	 */
	@Override
	public List<Measure> getfieldname(Measure m) throws DataAccessException {
		return (List<Measure>) getOracleSqlMapClientTemplate().queryForList("measure.queryfieldname", m);
	}

	/**
	 * 获取物料信息
	 */
	@Override
	public List<Map<String,String>> getmaterialname(Measure m) throws DataAccessException {
		return (List<Map<String,String>>) getOracleSqlMapClientTemplate().queryForList("measure.querymaterialname", m);
	}
	
	@Override
	public PageModel getmaterialname(Measure m,PageModel pm) throws DataAccessException {
		try{
            int count = (Integer) getOracleSqlMapClientTemplate().queryForObject("measure.querymaterial_count",m);
            pm.setAllcount(count);
            pm.setup();
            pm.setList(getOracleSqlMapClientTemplate().queryForList("measure.querymaterial",m, pm.getStart(), pm.getTruerows()));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return pm;
	}

	/**
	 * 获取毛重记录、皮重记录、净重记录
	 */
	@Override
	public PageModel getMeasurerecords(Measure m, PageModel pm) throws DataAccessException {
		int allnum = (Integer) getOracleSqlMapClientTemplate().queryForObject("measure.queryQYMeasure_count", m);
		pm.setAllcount(allnum);
		pm.setup();
		pm.setList(
				getOracleSqlMapClientTemplate().queryForList("measure.queryQYMeasure", m, pm.getStart(), pm.getTruerows()));
		return pm;
	}

	/**
	 * 获取上次皮重时间和重量
	 */
	@Override
	public Measure getTarebyCarno(Measure m) throws DataAccessException {
		return (Measure) getOracleSqlMapClientTemplate().queryForObject("measure.getTarebyCarno", m);
	}

	/**
	 * 获取业务类型名称
	 */
	@Override
	public String getOperaname(Measure m) throws DataAccessException {
		return (String) getOracleSqlMapClientTemplate().queryForObject("measure.getoperaname", m);
	}

	/**
	 * 获取L_plan_v 信息
	 */
	@Override

	public List<Measure> getlplanv(Measure m) throws DataAccessException {
		return (List<Measure>)getOracleSqlMapClientTemplate().queryForList("measure.getlplanv", m);
	}
	/**
	 * 
	  * @Title: getCompleteMeasure
	  * @Description:(根据车号查询此车无法过磅的数据信息)
	  * @param @param m
	  * @param @return
	  * @param @throws DataAccessException参数
	  * @return List<Measure>返回类型
	  * @throws
	 */
	public List<Measure> getCompleteMeasure(Measure m) throws DataAccessException {
		return (List<Measure>)getOracleSqlMapClientTemplate().queryForList("measure.getCompleteMeasure", m);
	}
	
	public List<Measure> getBeforesave(Measure m) throws DataAccessException {
		return (List<Measure>)getOracleSqlMapClientTemplate().queryForList("measure.getBeforesave", m);
	}
	
	//获取L_PLAN_V的信息
    public Measure getlplanvbyplanid(Measure m) throws DataAccessException{
    	return (Measure)getOracleSqlMapClientTemplate().queryForObject("measure.getlplanv", m);
    }

	@Override
	public int getGFlag(Measure measure) throws DataAccessException {
		return (int) getOracleSqlMapClientTemplate().queryForObject("measure.getGFlag", measure);
	}

	@Override
	public int getAccountstype(Measure measure) throws DataAccessException {
		return (int) getOracleSqlMapClientTemplate().queryForObject("measure.getAccountstype", measure);
	}
	
	@Override
	public List<Measure> getPrintDataInfos(String matchId) throws DataAccessException
	{
		return (List<Measure>)getOracleSqlMapClientTemplate().queryForList("measure.getMData", matchId);			 
	}
	/**
	 * 根据业务号查询打印次数
	 * @param matchId
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public int queryPrintnum(String taskcode) throws DataAccessException
	{
		return (Integer)getOracleSqlMapClientTemplate().queryForObject("measure.queryPrintnum", taskcode);			 
	}
	
	@Override
	public List<PrintModel> getPrintModelInfos(String op) throws DataAccessException
	{
		return (List<PrintModel>)getOracleSqlMapClientTemplate().queryForList("measure.getPrintModel", op);	
		
	}
	
	@Override
	public List<MeasureTareHistory> getTareHistory(MeasureTareHistory mth) throws DataAccessException
	{
		List<MeasureTareHistory> rtList=new ArrayList<MeasureTareHistory>();
		try {
			rtList= (List<MeasureTareHistory>)getOracleSqlMapClientTemplate().queryForList("measure.getTareHistory", mth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtList;
		
	}

	/*
	 * 保存计量信息
	 * */ 
	@SuppressWarnings("rawtypes")
	@Override
	public Measure saveMeasure(Measure measure) throws DataAccessException {
		Map map = new HashMap();
//		map.put("v_logid",measure.getLogid());
		map.put("v_rfidid", measure.getRfidno());
		map.put("v_rfidtype", measure.getRfidtype());
		map.put("v_rfidno", measure.getRfidno());
		map.put("v_icid", measure.getIcid());
		map.put("v_ictype", measure.getIctype());
		map.put("v_icno", measure.getIcno());
		map.put("v_cartype", measure.getCartype());
		map.put("v_planid", measure.getPlanid());
		map.put("v_taskcode", measure.getTaskcode());
		map.put("v_matchid",measure.getMatchid());
		map.put("v_matchidb",measure.getMatchidb());
		map.put("v_measurestate",measure.getMeasurestate() );
		map.put("v_operatype",measure.getOperatype() );
		map.put("v_carno",measure.getCarno());
		map.put("v_basket",measure.getBasket());
		map.put("v_materialcode",measure.getMaterialcode());
		map.put("v_materialname",measure.getMaterialname());
		map.put("v_materialspec",measure.getMaterialspec());
		map.put("v_materialspeccode",measure.getMaterialspeccode()  );
		map.put("v_materialcount",String.valueOf(measure.getMaterialcount()));
		map.put("v_shipcode", measure.getShipcode());
		map.put("v_ship",measure.getShip());
		map.put("v_sourcecode", measure.getSourcecode());
		map.put("v_sourcename",measure.getSourcename());
		map.put("v_sourceplace",measure.getSourceplace() );
		map.put("v_sourcetime",measure.getSourcetime() );
		map.put("v_sourceoperacode",measure.getSourceoperacode() );
		map.put("v_sourceoperaname",measure.getSourceoperaname() );
		map.put("v_targetcode",measure.getTargetcode() );
		map.put("v_targetname",measure.getTargetname() );
		map.put("v_targetplace",measure.getTargetplace() );
		map.put("v_targettime",measure.getTargettime() );
		map.put("v_targetoperacode",measure.getTargetoperacode() );
		map.put("v_targetoperaname",measure.getTargetoperaname() );
		map.put("v_gross",String.valueOf(measure.getGross()) );
		map.put("v_grosstime",measure.getGrosstime() );
		map.put("v_grossoperacode",measure.getGrossoperacode() );
		map.put("v_grossoperaname",measure.getGrossoperaname() );
		map.put("v_grossweighid",measure.getGrossweighid() );
		map.put("v_grossweigh",measure.getGrossweigh() );
		map.put("v_grossweighgroup",measure.getGrossweighgroup() );
		map.put("v_grossgroupno",String.valueOf(measure.getGrossgroupno()));
		map.put("v_grossserial",String.valueOf(measure.getGrossserial()) );
		map.put("v_grosslogid",measure.getGrosslogid() );
		map.put("v_grossspeed",String.valueOf(measure.getGrossspeed()) );
		map.put("v_tare",String.valueOf(measure.getTare()) );
		map.put("v_taretime",measure.getTaretime() );
		map.put("v_tareoperacode",measure.getTareoperacode() );
		map.put("v_tareoperaname",measure.getTareoperaname() );
		map.put("v_tareweighid",measure.getTareweighid() );
		map.put("v_tareweigh",measure.getTareweigh() );
		map.put("v_tareweighgroup",measure.getTareweighgroup() );
		map.put("v_taregroupno",String.valueOf(measure.getTaregroupno()) );
		map.put("v_tareserial",String.valueOf(measure.getTareserial()) );
		map.put("v_tarelogid",measure.getTarelogid() );
		map.put("v_tarespeed",String.valueOf(measure.getTarespeed()) );
		map.put("v_deduction",String.valueOf(measure.getDeduction()) );
		map.put("v_deduction2",String.valueOf(measure.getDeduction2()) );
		map.put("v_deductiontime",measure.getDeductiontime() );
		map.put("v_deductioncode",measure.getDeductioncode() );
		map.put("v_deductionname",measure.getDeductionname() );
		map.put("v_deductionoperacode",measure.getDeductionoperacode() );
		map.put("v_deductionoperaname",measure.getDeductionoperaname() );
		map.put("v_suttle",String.valueOf(measure.getSuttle()) );
		map.put("v_suttletime",measure.getSuttletime() );
		map.put("v_suttleoperacode",measure.getSuttleoperacode() );
		map.put("v_suttleoperaname",measure.getSuttleoperaname() );
		map.put("v_suttleweighid",measure.getSuttleweighid() );
		map.put("v_suttleweigh",measure.getSuttleweigh() );
		map.put("v_planweight",String.valueOf(measure.getPlanweight()) );
		map.put("v_planmaterialcount",String.valueOf(measure.getPlanmaterialcount()) );
		map.put("v_plancarcount",String.valueOf(measure.getPlancarcount()) );
		map.put("v_bflag",String.valueOf(measure.getBflag()) );
		map.put("v_dflag",String.valueOf(measure.getDflag()) );
		map.put("v_entertime",measure.getEntertime() );
		map.put("v_entergatecode",measure.getEntergatecode() );
		map.put("v_entergatename",measure.getEntergatename() );
		map.put("v_leavetime",measure.getLeavetime() );
		map.put("v_leavegatecode",measure.getLeavegatecode() );
		map.put("v_leavegatename",measure.getLeavegatename() );
		map.put("v_sampletime",measure.getSampletime() );
		map.put("v_grossb",String.valueOf(measure.getGrossb()) );
		map.put("v_grosstimeb",measure.getGrosstimeb() );
		map.put("v_grossoperacodeb",measure.getGrossoperacodeb() );
		map.put("v_grossoperanameb",measure.getGrossoperanameb() );
		map.put("v_grossweighidb",measure.getGrossweighidb() );
		map.put("v_grossweighb",measure.getGrossweighb() );
		map.put("v_grossweighgroupb",measure.getGrossweighgroupb() );
		map.put("v_grossgroupnob",String.valueOf(measure.getGrossgroupnob()) );
		map.put("v_grossserialb",String.valueOf(measure.getGrossserialb()) );
		map.put("v_grosslogidb",measure.getGrosslogidb() );
		map.put("v_grossspeedb",String.valueOf(measure.getGrossspeedb()) );
		map.put("v_tareb",String.valueOf(measure.getTareb()) );
		map.put("v_taretimeb",measure.getTaretimeb() );
		map.put("v_tareoperacodeb",measure.getTareoperacodeb() );
		map.put("v_tareoperanameb",measure.getTareoperanameb() );
		map.put("v_tareweighidb",measure.getTareweighidb() );
		map.put("v_tareweighb",measure.getTareweighb() );
		map.put("v_tareweighgroupb",measure.getTareweighgroupb() );
		map.put("v_taregroupnob",String.valueOf(measure.getTaregroupnob()) );
		map.put("v_tareserialb",String.valueOf(measure.getTareserialb()) );
		map.put("v_tarelogidb",measure.getTarelogidb() );
		map.put("v_tarespeedb",String.valueOf(measure.getTarespeedb()) );
		map.put("v_batchcode",measure.getBatchcode() );
		map.put("v_usermemo",measure.getUsermemo() );
		map.put("v_sysmemo",measure.getSysmemo() );
		map.put("v_clientid",measure.getClientid());  
		map.put("v_recordtype",measure.getRecordtype());
		map.put("v_sflag",String.valueOf(measure.getSflag()) );
		map.put("v_gflag",String.valueOf(measure.getGflag()) );
		map.put("v_mflag",String.valueOf(measure.getMflag()) );
		map.put("v_memo1",measure.getMemo1() );
		map.put("v_memo2",measure.getMemo2() );
		map.put("v_memo3",measure.getMemo3() );
		map.put("v_memo4",measure.getMemo4() );
		map.put("v_memo5",measure.getMemo5() );
		map.put("v_memo6",measure.getMemo6() );
		map.put("v_memo7",measure.getMemo7() );
		map.put("v_memo8",measure.getMemo8() );
		map.put("v_memo9",measure.getMemo9() );
		map.put("v_memo10",measure.getMemo10() );
		map.put("v_memo11",measure.getMemo11() );
		map.put("v_memo12",measure.getMemo12() );
		map.put("v_memo13",measure.getMemo13() );
		map.put("v_memo14",measure.getMemo14() );
		map.put("v_memo15",measure.getMemo15() );	
		getOracleSqlMapClientTemplate().insert("measure.saveMeasure",map);
		int i = (Integer)map.get("v_flag");
		String msg = (String)map.get("v_msg");
		measure.setFlag(i);
		measure.setMsg(msg);
		
		return measure;
	}

	@Override
	public List<PrintModel> getAllPrintModelData() throws DataAccessException
	{
		return (List<PrintModel>)getOracleSqlMapClientTemplate().queryForList("measure.getAllPrintModelData");	
		
	} 
	
 
	@Override
	public String getModelCount(String modelCode) throws DataAccessException
	{
		return (String)getOracleSqlMapClientTemplate().queryForObject("measure.getModelCount",modelCode);	
	}
	
	
	//新增打印模板 
	@Override
	public int addPrintModel(PrintModel pm) throws DataAccessException{
		int rti=0;
		try {
			 getOracleSqlMapClientTemplate().insert("measure.addPrintModel",pm);
			 rti=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rti;
	}
		
	//修改打印模板
	@Override
	public int modifyPrintModel(PrintModel pm) throws DataAccessException{
		int rti=0;
		try {
			rti=(int)getOracleSqlMapClientTemplate().update("measure.modifyPrintModel",pm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rti;
	}


	@Override
	public List<Map<String, String>> getOperateType() throws DataAccessException {
		return (List<Map<String, String>>)getOracleSqlMapClientTemplate().queryForList("measure.queryoperatetype");
	}

	@Override
	public List<Map<String, String>> getMaterialClasses(String search) throws DataAccessException {
		return (List<Map<String, String>>)getOracleSqlMapClientTemplate().queryForList("measure.querymaterialclass",search);
	}
	
	@Override
	public List<Map<String, String>> getMaterialClassesByName() throws DataAccessException {
		return (List<Map<String, String>>)getOracleSqlMapClientTemplate().queryForList("measure.queryallmaterialclassbyname");
	}
	
	/**
	 * 获取根据计划号求和之后的重量、支数、车数 信息
	 */
	@Override
	public Measure getPlanSumInfo(String planid) throws DataAccessException {
		return (Measure) getOracleSqlMapClientTemplate().queryForObject("measure.getPlanSumInfo", planid);
	}
	
	@Override
	public List<Measure> getSearchInfo(Measure m) throws DataAccessException {
		return (List<Measure>) getOracleSqlMapClientTemplate().queryForList("measure.getSearchInfo", m);
	}
	
	@Override
	public HashMap<String,String> getPrintBillStyle(String matchid) throws DataAccessException {
		return (HashMap<String,String>) getOracleSqlMapClientTemplate().queryForObject("measure.getPrintBillStyle",matchid);
	}
	@Override
	public HashMap<String,String> getConfigPrintStyle(String operatype) throws DataAccessException {
		return (HashMap<String,String>) getOracleSqlMapClientTemplate().queryForObject("measure.getConfigPrintStyle",operatype);
	}
	
	@Override
	public HashMap<String,String> get(String matchid) throws DataAccessException {
		HashMap<String,String> map = new HashMap<String,String>(1);
		map.put("matchid", matchid);
		return (HashMap<String,String>)getOracleSqlMapClientTemplate().queryForObject("measure.query_all_columns",map);
	}
	
	@Override
	public List<Measure> getByCarno(String carno) throws DataAccessException {
		HashMap<String,String> map = new HashMap<String,String>(1);
		map.put("carno", carno);
		return (List<Measure>)getOracleSqlMapClientTemplate().queryForList("measure.query_all_objects",map);
	}
	
	@Override
	public Measure getByMatchid(String matchid) throws DataAccessException{
		HashMap<String,String> map = new HashMap<String,String>(1);
		map.put("matchid", matchid);
		return (Measure)getOracleSqlMapClientTemplate().queryForObject("measure.query_all_objects",map);
	}

	@Override
	public HashMap<String,String> get(String matchid, String columns) throws DataAccessException {
		HashMap<String,String> map = new HashMap<String,String>(3);
		map.put("matchid", matchid);
		map.put("columns",columns);
		return (HashMap<String,String>)getOracleSqlMapClientTemplate().queryForObject("measure.query_sub_columns",map);
	}
	
	//查询是否有配置模板
	@Override
	public int selectConfigmodel(ConfigModel configmodel) throws DataAccessException{
		int rti=0;
		try {
			rti=(int)getOracleSqlMapClientTemplate().queryForObject("measure.selectConfigmodel",configmodel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rti;
	}
	
	public int insertConfigmodel(ConfigModel configmodel) throws DataAccessException{
		int rti=0;
		try {
			getOracleSqlMapClientTemplate().insert("measure.insertConfigmodel",configmodel);
			rti=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rti;
	}
	public int updateconfigmodel(ConfigModel configmodel) throws DataAccessException{
		int rti=0;
		try {
			rti=(int)getOracleSqlMapClientTemplate().update("measure.updateconfigmodel",configmodel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rti;
	}
	
	public List<TaskCode> getAllTaskcodes() throws DataAccessException{
		return (List<TaskCode>)getOracleSqlMapClientTemplate().queryForList("measure.getAllTaskcode");
	}
	
	public List<Measure> queryscaledetail(Measure measure) throws DataAccessException{
		return (List<Measure>)getOracleSqlMapClientTemplate().queryForList("measure.queryscaledetail",measure);
	}
	/**
	 * 根据业务号查询业务号信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public Measure queryTaskcode(Measure measure) throws DataAccessException{
		return (Measure)getOracleSqlMapClientTemplate().queryForObject("measure.queryTaskcode",measure);
	}
	
	
	
	public int insertscaledetail(Measure measure) throws DataAccessException{
		int flag=0;
		//根据物流号和序号查询信息是否存在
		int i = (Integer)getOracleSqlMapClientTemplate().queryForObject("measure.queryscaledetailcount",measure );
		if(i==0){//为0时添加信息
			flag=(Integer)getOracleSqlMapClientTemplate().insert("measure.insertscaledetail",measure);
			
		}else if(i>0){//大于0时，修改信息
			flag=getOracleSqlMapClientTemplate().update("measure.updatescaledetail",measure);
		}
		
		return flag;
	}
	/**
	 * 作废合金信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	
	public int cancelWeighterdata(Measure measure) throws DataAccessException{
		int flag=0;
		//根据物流号和序号查询信息是否存在
		Integer i = (Integer)getOracleSqlMapClientTemplate().queryForObject("measure.queryWeight",measure);
		if(i!=null){
			if(i>0){//大于0时，修改信息
				measure.setWeight(i);
				getOracleSqlMapClientTemplate().update("measure.cancelscaledetail",measure);
				getOracleSqlMapClientTemplate().update("measure.updateWeight",measure);
				flag=1;
			}
		}
		
		
		return flag;
	}
	
	/**
	 * 根据车号查询厂内最新的毛重
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public String queryMaxgrosstime(String carno) throws DataAccessException{
		return (String)getOracleSqlMapClientTemplate().queryForObject("measure.queryMaxgrosstime",carno);
	}
	/**
	 * 根据车号查询业务临时表是否有单独计皮的信息
	 * @param measure
	 * @return
	 * @throws DataAccessException
	 */
	public String queryzcount(String carno) throws DataAccessException{
		return (String)getOracleSqlMapClientTemplate().queryForObject("measure.queryzcount",carno);
	}
	
	
}
