package com.talent.measure.dao.impl;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.base.model.PageModel;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.ReportunitDao;
import com.talent.measure.model.Reportunit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Service
@Component
@SuppressWarnings({"deprecation","unchecked"})
public class ReportunitDaoImpl extends BaseDaoiBatis implements ReportunitDao {

	/**
	 * 查询统计单位信息
	 */
	@Override
	public PageModel queryReportunit(Reportunit re, PageModel pm) throws DataAccessException {
		String op="";
		List<Object> info = new ArrayList<Object>();
		try {
			
			int count = (Integer) getOracleSqlMapClientTemplate().queryForObject("Reportunit.queryReportunit_count", re);
			pm.setAllcount(count);
			pm.setup();
			List<Reportunit> list =getOracleSqlMapClientTemplate().queryForList("Reportunit.queryReportunit", re, pm.getStart(),
					pm.getTruerows());
			for(int i=0;i<list.size();i++){
				Reportunit rp = list.get(i);
				if(rp.getOperatype()!=null){
					String[] operatype = rp.getOperatype().split(",");				
					for(int m=0;m<operatype.length;m++){
						if(operatype.length==1){
							op=operatype[m];
						}else{
							if(m==0){
								op=operatype[m]+"',";
							}else if(m==operatype.length-1){
							    op=op+"'"+operatype[m];
							}else{
								 op=op+"'"+operatype[m]+"',";
							} 
						}
						
					}
				}
				
				String operaname=(String)getOracleSqlMapClientTemplate().queryForObject("Reportunit.queryoperaname",op);
				rp.setOperatype(operaname);
				info.add(rp);
			}
			pm.setList(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	/**
	 * 添加单位
	 */
	@Override
	public int insertReportunit(Reportunit re)throws DataAccessException{
		return (Integer)getOracleSqlMapClientTemplate().insert("Reportunit.insertReportunit",re);
	}
	/**
	 * 更新单位
	 */
	@Override
	public int updateReportunit(Reportunit re)throws DataAccessException{
		return getOracleSqlMapClientTemplate().update("Reportunit.updateReportunit",re);
	}
	/**
	 * 作废单位
	 */
	@Override
	public int cancelReportunit(Reportunit re)throws DataAccessException{
		return (Integer)getOracleSqlMapClientTemplate().delete("Reportunit.cancelReportunit",re);
	}
	/**
	 * 通过id查询单位信息
	 */
	@Override
	public Reportunit queryReportunitByid(Reportunit re)throws DataAccessException{
		return (Reportunit)getOracleSqlMapClientTemplate().queryForObject("Reportunit.queryReportunitByid",re);
	}
	/**
	 * 查询统计单位是否存在
	 */
	@Override
	public int queryCount(Reportunit re)throws DataAccessException{
		return (Integer)getOracleSqlMapClientTemplate().queryForObject("Reportunit.queryCount",re);
	}

	
	  
}
