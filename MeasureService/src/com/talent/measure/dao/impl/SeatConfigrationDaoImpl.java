package com.talent.measure.dao.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import com.talent.base.dao.impl.BaseDaoiBatis;
import com.talent.dubbo.config.api.annotation.Service;
import com.talent.measure.dao.SeatConfigrationDao;
import com.talent.measure.model.EquipmentParam;
import com.talent.measure.model.SeatClientId;
import com.talent.measure.model.SeatConfigration;

@Service
@Component
@SuppressWarnings({"deprecation","unchecked"})
public class SeatConfigrationDaoImpl extends BaseDaoiBatis implements SeatConfigrationDao {
	
	/**
	 * 获取衡器信息
	 */
	@Override
	public List<SeatConfigration> getSeatList(String equtype) throws DataAccessException{
		SeatConfigration seat = new SeatConfigration();
		seat.setEqutype(equtype);
		
			List<SeatConfigration> list  = new ArrayList<SeatConfigration>();	
			try{
				list = getOracleSqlMapClientTemplate().queryForList("seatconfiguration.getSeatList", seat);
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
	}
	/**
	 * 获取秤点对应的衡器信息
	 */
	@Override
	public List<SeatClientId> getSeatClient(SeatClientId sc) throws DataAccessException{		 
		List<SeatClientId> list  = new ArrayList<SeatClientId>();	
		try{
			list = getOracleSqlMapClientTemplate().queryForList("seatconfiguration.getSeatClient", sc);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取秤点对应的参数的最新版本号
	 */
	@Override
	public int  getLastVersionNum(String equcode) throws DataAccessException{	 
		
		return (int) getOracleSqlMapClientTemplate().queryForObject("seatconfiguration.getLastVersionNum", equcode);
		
	}
	
	/**
	 * 获取衡器获取对应的版本的配置参数信息
	 */
	@Override
	public List<EquipmentParam> getEquParamInfo(EquipmentParam sc) throws DataAccessException
	{
		List<EquipmentParam> list  = new ArrayList<EquipmentParam>();	
		try{
			list = getOracleSqlMapClientTemplate().queryForList("seatconfiguration.getEquParamInfo", sc);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	//保存秤体对应的配置参数信息
	@Override
	public int saveEquParamInfo(EquipmentParam eq) throws DataAccessException
	{
		int rti=0;
		try {
			try{
				getOracleSqlMapClientTemplate().insert("seatconfiguration.insertEquParamInfo",eq);
			}catch(DuplicateKeyException e){
				getOracleSqlMapClientTemplate().update("seatconfiguration.updateEquParamInfo",eq);
			}
			rti=1;
		} catch (Exception e) {
			e.printStackTrace();
			rti=0;
		}
		return rti;
	}
	
	//判断坐席与秤体对应关系是不是存在
	@Override
	public int checkSeatClientIsAdd(String seatid) throws DataAccessException
	{
		int rti=0;
		try {
			 rti=(int)getOracleSqlMapClientTemplate().queryForObject("seatconfiguration.checkSeatClientIsAdd",seatid);
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rti=0;
		}
		return rti;
	}
	//修改坐席与秤点对应关系
	@Override
	public int updateSeatClient(SeatClientId sc) throws DataAccessException
	{
		int rti=0;
		try {
			 getOracleSqlMapClientTemplate().update("seatconfiguration.updateSeatClient",sc);
			 rti=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rti=0;
		}
		return rti;
	}
	//新增坐席与秤点对应关系
	@Override
	public int insertSeatClient(SeatClientId sc) throws DataAccessException
	{
		int rti=0;
		try {
			 getOracleSqlMapClientTemplate().insert("seatconfiguration.insertSeatClient",sc);
			 rti=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rti=0;
		}
		return rti;
	}
	
	/**
	 * 获取衡器对应的坐席信息
	 */
	@Override
	public List<SeatClientId> getEqucodeSeat(SeatClientId sc) throws DataAccessException{		 
		List<SeatClientId> list  = new ArrayList<SeatClientId>();	
		try{
			list = getOracleSqlMapClientTemplate().queryForList("seatconfiguration.getEqucodeSeat", sc);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
