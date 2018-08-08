package com.talent.materialflow.service;

import org.springframework.dao.DataAccessException;

import com.talent.core.model.Message;
import com.talent.materialflow.model.Interface;

public interface BillService {

	/**
	 * 添加发运单、到货单
	 */

	Message insertBill(Interface inter) throws DataAccessException;

	/**
	 * 修改发运单、到货单
	 */

	Message updateBill(Interface inter) throws DataAccessException;

	/**
	 * 作废发运单、到货单
	 */

	Message cancelBill(Interface inter) throws DataAccessException;

}