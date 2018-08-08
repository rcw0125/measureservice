package com.xgmes.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xgmes.model.Customer;

public interface CustomerMapper {
	
	public List<Customer> queryList(Customer customer) throws DataAccessException;
	
	public Customer queryInfoById(Customer customer) throws DataAccessException;

	public int insertCustomer(Customer customer) throws DataAccessException;

	public int updateCustomer(Customer customer) throws DataAccessException;

	public int cancelCustomer(Customer customer) throws DataAccessException;

	public int queryCount(Customer customer) throws DataAccessException;
	
	public int queryCountById(Customer customer) throws DataAccessException;

}
