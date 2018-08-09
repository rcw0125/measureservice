package com.talent.measure.dao.impl;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.base.model.PageModel;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.ExceptionDao;
import com.talent.measure.model.Exceptions;
import com.talent.measure.model.MeasurePhoto;
import com.talent.measure.model.Operaconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Service
@Component
@SuppressWarnings({ "deprecation", "unchecked" })
public class ExceptionDaoImpl extends BaseDaoiBatis implements ExceptionDao {

	/*
	 * 查询可以修改的信息列表
	 */
	@Override

	public PageModel queryException(Exceptions m, PageModel pm) throws DataAccessException {
		try {
			int count = (Integer) getOracleSqlMapClientTemplate().queryForObject("exceptions.queryException_count", m);
			pm.setAllcount(count);
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("exceptions.queryException", m, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	/*
	 * 根据物流号查询修改的数据信息
	 */
	@Override

	public Exceptions queryInfo(String m) throws DataAccessException {
		Exceptions measure = (Exceptions) getOracleSqlMapClientTemplate().queryForObject("exceptions.queryInfo", m);
		return measure;
	}
	/*
	 * 保存计量信息
	 */

	@Override
	@SuppressWarnings("rawtypes")
	public Exceptions saveException(Exceptions exception) throws DataAccessException {
		Map map = new HashMap();
//		System.out.println("输出i.日日日.." + exception.getApplicationno());
		map.put("v_applicationno", exception.getApplicationno());
		map.put("v_gross", String.valueOf(exception.getGross()*1000));
		map.put("v_grosstime", exception.getGrosstime());
		map.put("v_grossoperacode", exception.getGrossoperacode());
		map.put("v_grossoperaname", exception.getGrossoperaname());
		map.put("v_grossweighid", exception.getGrossweighid());
		map.put("v_grossweigh", exception.getGrossweigh());
		map.put("v_tare", String.valueOf(exception.getTare()*1000));
		map.put("v_taretime", exception.getTaretime());
		map.put("v_tareoperacode", exception.getTareoperacode());
		map.put("v_tareoperaname", exception.getTareoperaname());
		map.put("v_tareweighid", exception.getTareweighid());
		map.put("v_tareweigh", exception.getTareweigh());
		map.put("v_usermemo", exception.getUsermemo());
		map.put("v_createman", exception.getCreateman());
//		System.out.println("输出i.日日日.123." + map.get("v_usermemo"));
		getOracleSqlMapClientTemplate().insert("exceptions.saveException", map);
		int i = (Integer) map.get("v_flag");
		String msg = (String) map.get("v_msg");
		exception.setFlag(i);
		exception.setMsg(msg);

		return exception;
	}
	/*
	 * 查询计量衡器配置信息
	 */
	@Override

	public PageModel queryMeasureweighinfo(Exceptions m, PageModel pm) throws DataAccessException {
		try {
			int count = (Integer) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryMeasureweighinfo_count", m);
			pm.setAllcount(count);
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("businessconfig.queryMeasureweighinfo", m, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	/*
	 * 修改衡器配置状态
	 * */
	public int cancelMeasureweigh(Exceptions e)throws DataAccessException {
	
		return   getOracleSqlMapClientTemplate().update("businessconfig.cancelMeasureweigh", e);
	}
	/*
	 * 添加衡器配置
	 * */
	public int insertMeasureweigh(Exceptions e)throws DataAccessException {
		
		return   (Integer)getOracleSqlMapClientTemplate().insert("businessconfig.insertMeasureweigh", e);
	}
	/*
	 * 修改衡器配置
	 * */
	public int updateMeasureweigh(Exceptions e)throws DataAccessException {
		
		return   (Integer)getOracleSqlMapClientTemplate().update("businessconfig.updateMeasureweigh", e);
	}
	/*
	 * 根据物流号查询修改的数据信息
	 */
	@Override

	public Exceptions queryMweighinfoByid(String m) throws DataAccessException {
		Exceptions e = (Exceptions) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryMweighinfoByid", m);
		return e;
	}
	
	
	/*
	 * 根据衡器编码查询衡器名称
	 */
	@Override

	public List<Exceptions> queryEqumentByid(Exceptions m) throws DataAccessException {
		return (List<Exceptions>) getOracleSqlMapClientTemplate().queryForList("businessconfig.queryEqumentByid", m);
		 
	}
	/*
	 * 查询业务类型
	 * */
	@Override
	public List<MeasurePhoto> queryOperatype() throws DataAccessException {
		List<MeasurePhoto> e = getOracleSqlMapClientTemplate().queryForList("exceptions.queryOperatype");
		return e;
	}
	/*
	 * 查询衡器集合
	 * */
	@Override
	public List<MeasurePhoto> queryEquipment() throws DataAccessException {
		List<MeasurePhoto> e = getOracleSqlMapClientTemplate().queryForList("exceptions.queryEquipment");
		return e;
	}
	
	/*
	 * 业务类型业务控制配置
	 * */
	@Override

	public Operaconfig queryOperaconfig(String operatype) throws DataAccessException {
/*//		try {
//			int count = (Integer) getOracleSqlMapClientTemplate().queryForObject("exceptions.queryOperaconfig_count");
//			pm.setAllcount(count);
//			pm.setup();
//			pm.setList(getOracleSqlMapClientTemplate().queryForList("exceptions.queryOperaconfig",  pm.getStart(),
//					pm.getTruerows()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
 * 
*/		Operaconfig o = (Operaconfig)getOracleSqlMapClientTemplate().queryForObject("exceptions.queryOperaconfig",operatype);
		return o;
	}
	
	/*
	 * 修改流程业务配置
	 * */
	public int updateOperaconfig(Operaconfig e)throws DataAccessException {
		
		return   (Integer)getOracleSqlMapClientTemplate().update("exceptions.updateOperaconfig", e);
	}
}
