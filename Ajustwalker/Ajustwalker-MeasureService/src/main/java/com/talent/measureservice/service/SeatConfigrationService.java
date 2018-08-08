package com.talent.measureservice.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.talent.measureservice.model.EquipmentParam;
import com.talent.measureservice.model.SeatClientId;

public interface SeatConfigrationService {

	// 保存秤体对应的配置参数信息

	int saveEquParamInfo(List<EquipmentParam> mtd) throws DataAccessException;

	// 修改坐席与秤点对应关系

	int updateSeatClient(List<SeatClientId> scList) throws DataAccessException;

}