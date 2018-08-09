package com.talent.measure.dao.impl;

import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.base.model.PageModel;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.BusinessConfigDao;
import com.talent.measure.model.BusinessConfig;
import com.talent.measure.model.Card;
import com.talent.measure.model.Equipment;
import com.talent.measure.model.FunctionLog;
import com.talent.measure.model.Measure;
import com.talent.measure.model.Mointor;
import com.talent.measure.model.Msameweight;
import com.talent.measure.model.TareLog;
import com.talent.measure.model.WeighterData;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Service
@Component
@SuppressWarnings({ "deprecation", "unchecked" })
public class BusinessConfigDaoImpl extends BaseDaoiBatis implements BusinessConfigDao {

	/*
	 * 根据业务类型查询重量相似的相关配置
	 */

	@Override
	public BusinessConfig querySameweightconfig(BusinessConfig bc) throws DataAccessException {

		return (BusinessConfig) getOracleSqlMapClientTemplate().queryForObject("businessconfig.querySameweightconfig",
				bc);
	}
	/*
	 * 获取相似的计量信息
	 */

	@Override
	public List<Measure> querySameweightInfo(Measure bc) throws DataAccessException {

		return (List<Measure>) getOracleSqlMapClientTemplate().queryForList("businessconfig.querySameweightInfo", bc);
	}

	@Override
	public Double queryLasttare(String carno) throws DataAccessException {

		return (Double) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryLasttare", carno);
	}
	/*
	 * 查询已经执行的计划量
	 */

	@Override
	public Measure queryRealsuttle(Measure bc) throws DataAccessException {

		return (Measure) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryRealsuttle", bc);
	}
	/*
	 * *查询已经执行的支数
	 */

	@Override
	public Measure queryRealmaterialcount(Measure bc) throws DataAccessException {

		return (Measure) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryRealmaterialcount", bc);
	}

	/*
	 * 查询已经执行的车数
	 */
	@Override
	public Measure queryRealcarcount(Measure bc) throws DataAccessException {

		return (Measure) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryRealcarcount", bc);
	}

	/*
	 * 查询历史皮重
	 * 
	 */
	@Override
	public List<TareLog> queryHistorytare(Measure measure) throws DataAccessException {

		return (List<TareLog>) getOracleSqlMapClientTemplate().queryForList("businessconfig.queryHistorytare", measure);
	}

	/*
	 * 获取计量衡器
	 */

	@Override
	public int queryMeasureweigh(Map<String, String> map) throws DataAccessException {
		int i = (Integer) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryMeasureweighBM", map);
		/*
		 * if(i==0){ i =(Integer)getOracleSqlMapClientTemplate().queryForObject(
		 * "businessconfig.queryMeasureweigh", map); }
		 */
		return i;
	}

	public int insertfunctionlog(FunctionLog flog) {
		getOracleSqlMapClientTemplate().insert("businessconfig.insertfunctionlog", flog);
		return 1;
	}

	public List<WeighterData> querytimeBymatchid(WeighterData ex) throws DataAccessException {

		return (List<WeighterData>) getOracleSqlMapClientTemplate().queryForList("businessconfig.querytimeBymatchid",
				ex);
	}

	public Equipment queryrange(Equipment e) throws DataAccessException {
		return (Equipment) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryrange", e);
	}

	public PageModel queryMointorinfo(Mointor m, PageModel pm) throws DataAccessException {
		try {
			int count = (Integer) getOracleSqlMapClientTemplate().queryForObject("Mointor.queryMointorinfo_count", m);
			pm.setAllcount(count);
			pm.setup();
			pm.setList(getOracleSqlMapClientTemplate().queryForList("Mointor.queryMointorinfo", m, pm.getStart(),
					pm.getTruerows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	public Card queryRfidBycarno(Card card) throws DataAccessException {
		return (Card) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryRfidBycarno", card);
	}
	/**
	 * 根据物料编码查询属性
	 * @param materialcode
	 * @return
	 * @throws DataAccessException
	 *//*
	public String queryInfoBymateiracode(String materialcode) throws DataAccessException {
		return (String) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryInfoBymateiracode", materialcode);
	}*/
	/**
	 * 是否需要发放rfid
	 * 
	 * @param matchids
	 * @return
	 * @throws DataAccessException
	 */
	public String queryInfoBymateiracode(String mateiralcode, String type) throws DataAccessException {
		String info = "";
		String msg = (String) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryInfoBymateiracode", mateiralcode);
		if (msg == null) {
			info = "-1";
		} else {
			String[] array = msg.split(",");
			if ("1".equals(type)) {// 第一个路线id 默认0，第二个rfid 默认-1，第三个 是否计量  默认-1，第四个是否监秤
				info = array[0];
			} else if ("2".equals(type)) {
				info = array[1];
			} else if ("3".equals(type)) {
				info = array[2];
			} else if ("4".equals(type)) {
				info = array[3];
			}
		}

		return info;

	}
	public int queryNode(String matchid,String nodecode) throws DataAccessException {
		Msameweight ms = new Msameweight();
		ms.setMatchid(matchid);
		ms.setNodecode(nodecode);
		return (Integer) getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryNode", ms);
	}
	
	public Msameweight queryLastnode(String matchid,String nodes)throws DataAccessException{
		/**
		 * 根据物流号查询作业路线
		 */
		Msameweight ms = new Msameweight();
		ms.setMatchid(matchid);
		ms.setNodecode(nodes);
		String routeid =(String)getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryRouteidbymatchid", matchid); 
		ms.setRouteid(routeid);
		/**
		 * 根据路线和当前环节判断
		 */
		String nodecode=(String)getOracleSqlMapClientTemplate().queryForObject("businessconfig.queryLastNode", ms);
		ms.setNodecode(nodecode);
		return ms;
	}
	/**
	 * 根据路线和作业环节查作业点
	 * @param matchid
	 * @param nodes
	 * @return
	 * @throws DataAccessException
	 */
	public String queryworkpointname(String node)throws DataAccessException{
		String workpoint="";		
		/*String[] list = nodecode.split(",");
        
        if(list.length==1){
        	node = "'" + list[0] + "'"; ;
        }else{
        	for (int i = 0; i < list.length; i++) {
				if (i == 0) {
					node = "'" + list[i] + "',";
				} else if (i == list.length - 1) {
					node = node + "'" + list[i]  + "'";
				} else {
					node = node + "'" + list[i]  + "',";
				}
			}
        }		
		if (list.length > 0) {
			 workpoint=(String)getOracleSqlMapClientTemplate().queryForObject("businessconfig.querywnamebycode", node);
		}		*/
		return workpoint;
	}
	   
}
