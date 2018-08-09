package com.talent.measure.dao.impl;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.base.model.PageModel;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.MsameweightDao;
import com.talent.measure.model.Msameweight;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Service
@Component
@SuppressWarnings({ "deprecation" , "unchecked"})
public class MsameweightDaoImpl extends BaseDaoiBatis implements MsameweightDao {

	/*
	 * 相似重量操作，如果存在作废原来数据，如果没有插入新的数据信息
	 */

	@Override
	public int insertConfigmodel(Msameweight ms) throws DataAccessException {
		int rti = 0;
		try {

			int i = (Integer) getOracleSqlMapClientTemplate().queryForObject("msameweight.queryMcount", ms);
			if (i > 0) { // 如果存在作废原来数据
				getOracleSqlMapClientTemplate().update("msameweight.updateValidflag", ms);
			}
			String[] matchids = ms.getMatchidlist().split(",");
			for (int s = 0; s < matchids.length; s++) {
				ms.setMatchid(matchids[s]);
				getOracleSqlMapClientTemplate().insert("msameweight.insertmsweight", ms);
			}
			ms.setMatchid(ms.getFmatchid());
			getOracleSqlMapClientTemplate().insert("msameweight.insertmsweight", ms);
			rti = 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rti;
	}
	@Override
	public PageModel querySameweight(Msameweight m, PageModel pm) throws DataAccessException {
		try {
			int count = (Integer) getOracleSqlMapClientTemplate().queryForObject("msameweight.querySameweight_count", m);
			pm.setAllcount(count);
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("msameweight.querySameweight", m, pm.getStart(),pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}


}
