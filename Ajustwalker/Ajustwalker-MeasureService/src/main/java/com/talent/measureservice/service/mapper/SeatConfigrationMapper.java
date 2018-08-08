package com.talent.measureservice.service.mapper;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.talent.measureservice.model.EquipmentParam;
import com.talent.measureservice.model.SeatClientId;
import com.talent.measureservice.model.SeatConfigration;

public interface SeatConfigrationMapper {

	/**
	 * 根据设备类型查询设备（终端、坐席）
	 * 
	 * @param app
	 * @return
	 * @throws DataAccessException
	 */
	public List<SeatConfigration> getSeatList(SeatClientId sc) throws DataAccessException;

	/**
	 * 获取秤点对应的衡器信息
	 */
	public List<SeatClientId> getSeatClient(SeatClientId sc) throws DataAccessException;

	/**
	 * 获取秤点对应的参数的最新版本号
	 */
	public int getLastVersionNum(String equcode) throws DataAccessException;

	/**
	 * 获取衡器获取对应的版本的配置参数信息
	 */
	public List<EquipmentParam> getEquParamInfo(EquipmentParam sc) throws DataAccessException;

	// 保存秤体对应的配置参数信息
	public int saveEquParamInfo(EquipmentParam eq) throws DataAccessException;

	// 判断坐席与秤体对应关系是不是存在
	public int checkSeatClientIsAdd(String seatid) throws DataAccessException;

	// 修改坐席与秤点对应关系
	public int updateSeatClient(SeatClientId sc) throws DataAccessException;

	// 新增坐席与秤点对应关系

	public int insertSeatClient(SeatClientId sc) throws DataAccessException;

	/**
	 * 获取衡器对应的坐席信息
	 */

	public List<SeatClientId> getEqucodeSeat(SeatClientId sc) throws DataAccessException;
}
