package com.talent.measure.dao.impl;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.base.model.PageModel;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.EquipmentDao;
import com.talent.measure.model.Equipment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Service
@Component
@SuppressWarnings({"deprecation","unchecked"})
public class EquipmentDaoImpl extends BaseDaoiBatis implements EquipmentDao {

	/* (non-Javadoc)
	 * 查询衡器信息
	 */
	@Override
	public PageModel queryEquiment(Equipment eq, PageModel pm) throws DataAccessException {
		try {
			int count = (Integer) getOracleSqlMapClientTemplate().queryForObject("Equipment.queryEquiment_count", eq);
			pm.setAllcount(count);
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("Equipment.queryEquiment", eq, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	/* (non-Javadoc)
	 * 添加衡器
	 * */
	@Override
	public int insertEquiment(Equipment eq)throws DataAccessException{
		return (Integer)getOracleSqlMapClientTemplate().insert("Equipment.insertEquiment",eq);
	}
	/* (non-Javadoc)
	 *修改衡器
	 **/
	@Override
	public int updateEquiment(Equipment eq)throws DataAccessException{
		return getOracleSqlMapClientTemplate().update("Equipment.updateEquiment",eq);
	}
	/* (non-Javadoc)
	 * 作废衡器
	 * */
	@Override
	public int cancelEquiment(Equipment eq)throws DataAccessException{
		return (Integer)getOracleSqlMapClientTemplate().update("Equipment.cancelEquiment",eq);
	}
	/**
	 * 根据衡器id查询衡器信息
	 * @param eq
	 * @return
	 * @throws DataAccessException
	 */
	public Equipment queryEquimentByid(Equipment eq)throws DataAccessException{
		return (Equipment)getOracleSqlMapClientTemplate().queryForObject("Equipment.queryEquimentByid",eq);
	}
	/**
	 * 通过衡器编码查询是否存在
	 * @param eq
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCBycode(Equipment eq)throws DataAccessException{
		return (Integer)getOracleSqlMapClientTemplate().queryForObject("Equipment.queryCBycode",eq);
	}
	/**
	 * 通过衡器编码查询是否存在
	 * @param eq
	 * @return
	 * @throws DataAccessException
	 */
	public int queryCByname(Equipment eq)throws DataAccessException{
		return (Integer)getOracleSqlMapClientTemplate().queryForObject("Equipment.queryCByname",eq);
	}
	
	  
}
