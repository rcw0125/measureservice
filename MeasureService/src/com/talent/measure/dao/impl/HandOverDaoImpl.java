package com.talent.measure.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.base.model.PageModel;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.HandOverDao;
import com.talent.measure.model.HandOver;


@Service
@Component
@SuppressWarnings({ "deprecation", "unchecked" })
public class HandOverDaoImpl extends BaseDaoiBatis implements HandOverDao {

	/*
	 * 查询信息列表
	 */

	@Override
	public PageModel queryHandover(HandOver m, PageModel pm) throws DataAccessException {
		try {
			int count = (Integer) getOracleSqlMapClientTemplate().queryForObject("handover.queryHandover_count", m);
			pm.setAllcount(count);
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("handover.queryHandover", m, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	/* (non-Javadoc)
	 * @see com.talent.measure.dao.impl.HandOverDao#insertHandover(com.talent.measure.model.HandOver)
	 */
	@Override
	public int insertHandover(HandOver h) throws DataAccessException {
		int rti = 0;
		try {
			if (h.getId() == -1) {
				getOracleSqlMapClientTemplate().insert("handover.insertHandover", h);
			} else {
				getOracleSqlMapClientTemplate().update("handover.updateHandover", h);
			}
			rti = 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rti;
	}

	/* (non-Javadoc)
	 * @see com.talent.measure.dao.impl.HandOverDao#cancelHandover(com.talent.measure.model.HandOver)
	 */
	@Override
	public int cancelHandover(HandOver h) throws DataAccessException {
		int rti = 0;
		try {
			rti = getOracleSqlMapClientTemplate().update("handover.cancelHandover", h);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rti;
	}
	@Override
	public HandOver queryHandoverByid(HandOver h) throws DataAccessException {
		
		try {
			h = (HandOver)getOracleSqlMapClientTemplate().queryForObject("handover.queryHandoverByid", h);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h;
	}


}
