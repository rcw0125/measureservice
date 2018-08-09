package com.talent.measure.dao;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.measure.model.EquipmentParam;
import com.talent.measure.model.SeatClientId;
import com.talent.measure.model.SeatConfigration;

public interface  SeatConfigrationDao {

	/**
	 * 获取衡器信息
	 */
	public List<SeatConfigration> getSeatList(String equtype) throws DataAccessException;
	
	/**
	 * 获取坐席对应的衡器
	 */
	public List<SeatClientId> getSeatClient(SeatClientId sc) throws DataAccessException;
	
	//获取秤体对应的最新参数版本号
	public int getLastVersionNum(String equcode) throws DataAccessException;
	
	/**
	 * 获取衡器获取对应的版本的配置参数信息
	 */
	public List<EquipmentParam> getEquParamInfo(EquipmentParam sc) throws DataAccessException;
	
	//保存秤体对应的参数信息
	public int saveEquParamInfo(EquipmentParam eq) throws DataAccessException; 
	
	//判断坐席与秤体对应关系是不是存在
	public int checkSeatClientIsAdd(String seatid) throws DataAccessException;
	
	//修改坐席与秤点对应关系
	public int updateSeatClient(SeatClientId sc) throws DataAccessException;
	
	//新增坐席与秤点对应关系
	public int insertSeatClient(SeatClientId sc) throws DataAccessException;
	
	/**
	 * 获取衡器对应的坐席
	 */
	public List<SeatClientId> getEqucodeSeat(SeatClientId sc) throws DataAccessException;
	
}
